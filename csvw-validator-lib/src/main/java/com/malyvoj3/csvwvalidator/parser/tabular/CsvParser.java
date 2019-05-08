package com.malyvoj3.csvwvalidator.parser.tabular;


import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import com.malyvoj3.csvwvalidator.domain.model.Column;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

@Slf4j
public class CsvParser implements TabularDataParser {

    private static final String FIELD_DELIMITER_DEFAULT = ",";
    private static final String LINE_SEPARATOR_DEFAULT = "\r\n";
    private static final char QUOTE_CHAR_DEFAULT = '"';
    private static final char QUOTE_ESCAPE_CHAR_DEFAULT = '"';

    @Override
    public TabularParsingResult parse(Dialect dialect, String url, String filePath, boolean remoteFile) {
        Table table = new Table();
        table.setUrl(url);
        List<Column> columns = new ArrayList<>();
        Long columnsNumber = null;
        Long rowsNumber = null;
        List<ColumnDescription> columnDescriptions = new ArrayList<>();
        TableDescription tableDescription = null;
        List<ValidationError> parsingErrors = new ArrayList<>();
        String resultFilePath = null;

        File csvFile = null;
        try {
            csvFile = new File(new URI(filePath));
        } catch (Exception ex) {
            log.error(String.format("Error during CSV parsing of file '%s'.", url), ex);
            parsingErrors.add(ValidationError.fatal("error.invalidCsv", url));
        }

        if (csvFile != null && csvFile.length() > 0) {
            boolean isUtf = true;
            try (ReadableByteChannel readerChannel = Channels.newChannel(new FileInputStream(csvFile))) {
                ByteBuffer buffer = ByteBuffer.allocate(10000);
                while (readerChannel.read(buffer) != -1) {
                    if (!FileUtils.isUtf8(buffer)) {
                        isUtf = false;
                        break;
                    }
                    buffer.clear();
                }
            } catch (Exception ex) {
                // nothing will fail on next try
            }

            if (!isUtf) {
                parsingErrors.add(ValidationError.strictWarn("error.notUtf"));
            }

            try {
                File tmpFile = File.createTempFile("tmp", null, new File("tmp"));
                try (Reader reader = new InputStreamReader(new FileInputStream(csvFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile))) {
                    com.univocity.parsers.csv.CsvParser csvParser = new com.univocity.parsers.csv.CsvParser(defaultSettings(dialect));
                    CsvWriter csvWriter = new CsvWriter(writer, mySettings());
                    csvParser.beginParsing(reader);
                    createColumns(csvParser, table, columns, columnDescriptions);
                    long rowNumber = dialect.isHeader() ? 2L : 1L;
                    String[] record;
                    while ((record = csvParser.parseNext()) != null) {
                        parsingErrors.addAll(createRow(record, columns, rowNumber));
                        csvWriter.writeRow(record);
                        rowNumber++;
                    }
                    writer.close();
                    parsingErrors.addAll(validateColumns(columns));
                    parsingErrors.addAll(validateCsvFormat(csvParser.getDetectedFormat()));
                    table.setColumns(columns);
                    columnsNumber = (long) columns.size();
                    rowsNumber = dialect.isHeader() ? rowNumber - 1 : rowNumber;
                    tableDescription = createTableDescription(columnDescriptions, url);
                    resultFilePath = UriUtils.normalizeUri(tmpFile.toURI().toString());
                } catch (CsvFormatException ex) {
                    log.error("Invalid CSF file format. Stop parsing.");
                    parsingErrors.add(ex.getValidationError());
                } catch (Exception ex) {
                    log.error(String.format("Error during CSV parsing of file '%s'.", url), ex);
                    parsingErrors.add(ValidationError.fatal("error.invalidCsv", url));
                }
            } catch (IOException e) {
                e.printStackTrace();
                // TODO
            }
        } else if (csvFile != null && csvFile.length() <= 0) {
            parsingErrors.add(ValidationError.fatal("error.emptyCsvFile", url));
        }

        if (remoteFile) {
            org.apache.commons.io.FileUtils.deleteQuietly(csvFile);
        }

        return new TabularParsingResult(url, parsingErrors, table, tableDescription, resultFilePath, rowsNumber, columnsNumber);
    }

    private CsvWriterSettings mySettings() {
        CsvFormat csvFormat = new CsvFormat();
        csvFormat.setComment('\0');
        csvFormat.setQuoteEscape(QUOTE_ESCAPE_CHAR_DEFAULT);
        csvFormat.setQuote(QUOTE_CHAR_DEFAULT);
        csvFormat.setDelimiter(FIELD_DELIMITER_DEFAULT);
        csvFormat.setLineSeparator(LINE_SEPARATOR_DEFAULT);
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setFormat(csvFormat);
        settings.trimValues(false);
        settings.setQuoteEscapingEnabled(true);
        settings.setMaxCharsPerColumn(8192);
        return settings;
    }

    private void createColumns(com.univocity.parsers.csv.CsvParser parser, Table table, List<Column> columns, List<ColumnDescription> columnDescriptions) {
        int columnNumber = 1;
        for (String header : parser.getContext().headers()) {
            // By default we are trimming.
            String trimmedHeader = StringUtils.trim(header);
            List<String> headerTitles = new ArrayList<>();
            headerTitles.add(trimmedHeader);
            Map<String, List<String>> titles = new HashMap<>();
            titles.put(CsvwKeywords.NATURAL_LANGUAGE_CODE, headerTitles);
            columns.add(Column.builder()
                    .titles(titles)
                    .name(trimmedHeader)
                    .number(columnNumber)
                    .build());
            columnDescriptions.add(createColumnDescription(trimmedHeader));
            columnNumber++;
        }
    }

    private List<ValidationError> createRow(String[] record, List<Column> columns, long rowNumber) throws CsvFormatException {
        List<ValidationError> parsingErrors = new ArrayList<>();
        int columnLength = columns.size();
        if (record.length > 0) {
            parsingErrors.addAll(validateRowLength(record.length, rowNumber, columnLength));
            for (int i = 0; i < record.length; i++) {
                String value = record[i];
                parsingErrors.addAll(validateValue(value, rowNumber, i));
                Column cellColumn = i < columns.size() ? columns.get(i) : null;
                if (cellColumn != null) {
                    if (cellColumn.isEmpty() && StringUtils.isNotBlank(value)) {
                        cellColumn.setEmpty(false);
                    }
                }
            }
        } else {
            parsingErrors.add(ValidationError.strictWarn("error.emptyRow", rowNumber));
        }
        return parsingErrors;
    }

    private List<ValidationError> validateRowLength(int recordLength, long rowNumber, int columnLength) {
        List<ValidationError> errors = new ArrayList<>();
        if (recordLength > columnLength) {
            errors.add(ValidationError.fatal(
                    "error.extraCell", rowNumber)
            );
        } else if (recordLength < columnLength) {
            errors.add(ValidationError.fatal(
                    "error.missingCell", rowNumber)
            );
        }
        return errors;
    }

    private List<ValidationError> validateColumns(List<Column> columns) {
        List<ValidationError> errors = new ArrayList<>();
        for (Column column : columns) {
            if (column.isEmpty()) {
                errors.add(ValidationError.strictWarn("error.emptyColumn", column.getName()));
            }
        }
        return errors;
    }

    private List<ValidationError> validateValue(String value, long rowNumber, int columnNumber) {
        List<ValidationError> errors = Collections.emptyList();
        if (value == null) {
            errors = Collections.emptyList();
        } else if (StringUtils.isWhitespace(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "error.justWhitespaces", rowNumber, columnNumber
            ));
        } else if ("null".equals(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "error.nullValue", rowNumber, columnNumber
            ));
        } else if (containsLeadingSpaces(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "error.leadingSpaces", rowNumber, columnNumber
            ));
        } else if (containsTrailingSpaces(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "error.trailingSpaces", rowNumber, columnNumber
            ));
        }
        return errors;
    }

    private boolean containsTrailingSpaces(String value) {
        return StringUtils.isNotEmpty(value) && Character.isWhitespace(value.charAt(value.length() - 1));
    }

    private boolean containsLeadingSpaces(String value) {
        return StringUtils.isNotEmpty(value) && Character.isWhitespace(value.charAt(0));
    }

    private List<ValidationError> validateCsvFormat(CsvFormat detectedFormat) {
        List<ValidationError> errorList = new ArrayList<>();
        if (!LINE_SEPARATOR_DEFAULT.equals(detectedFormat.getLineSeparatorString())) {
            errorList.add(ValidationError.strictWarn(
                    "error.csvFormat.lineSeparator",
                    escapeLineSeparator(detectedFormat.getLineSeparatorString()), escapeLineSeparator(LINE_SEPARATOR_DEFAULT)
            ));
        }
        if (!FIELD_DELIMITER_DEFAULT.equals(detectedFormat.getDelimiterString())) {
            errorList.add(ValidationError.strictWarn(
                    "error.csvFormat.fieldDelimiter",
                    detectedFormat.getDelimiter() == '\t' ? "\\t" : detectedFormat.getDelimiterString(), FIELD_DELIMITER_DEFAULT
            ));
        }
        if (QUOTE_CHAR_DEFAULT != detectedFormat.getQuote()) {
            errorList.add(ValidationError.strictWarn(
                    "error.csvFormat.quote",
                    String.valueOf(detectedFormat.getQuote()), String.valueOf(QUOTE_CHAR_DEFAULT)
            ));
        }
        if (QUOTE_ESCAPE_CHAR_DEFAULT != detectedFormat.getQuoteEscape()) {
            errorList.add(ValidationError.strictWarn(
                    "error.csvFormat.quoteEscape",
                    String.valueOf(detectedFormat.getQuoteEscape()), String.valueOf(QUOTE_CHAR_DEFAULT)
            ));
        }
        return errorList;
    }

    private String escapeLineSeparator(String string) {
        String result = null;
        if (string != null) {
            result = string.replace("\r", "\\r").replace("\n", "\\n");
        }
        return result;
    }

    private static CsvParserSettings defaultSettings(Dialect dialect) {
        CsvFormat csvFormat = new CsvFormat();
        csvFormat.setComment('\0');
        csvFormat.setQuoteEscape(QUOTE_ESCAPE_CHAR_DEFAULT);
        csvFormat.setQuote(QUOTE_CHAR_DEFAULT);
        CsvParserSettings settings = new CsvParserSettings();
        settings.setFormat(csvFormat);
        settings.setDelimiterDetectionEnabled(true, ',', ';', '\t', '|');
        settings.trimValues(false);
        settings.setLineSeparatorDetectionEnabled(true);
        settings.setQuoteDetectionEnabled(false);
        settings.setSkipEmptyLines(false);
        settings.setHeaderExtractionEnabled(dialect.isHeader());
        settings.setMaxCharsPerColumn(8192);
        return settings;
    }

    private ColumnDescription createColumnDescription(String header) {
        ColumnDescription columnDescription = new ColumnDescription();
        if (StringUtils.isNotBlank(header)) {
            Map<String, List<String>> titlesMap = new HashMap<>();
            List<String> titlesList = new ArrayList<>();
            titlesList.add(header);
            titlesMap.put(CsvwKeywords.NATURAL_LANGUAGE_CODE, titlesList);
            NaturalLanguageProperty titles = new NaturalLanguageProperty(titlesMap);
            columnDescription.setTitles(titles);
        }
        return columnDescription;
    }

    private TableDescription createTableDescription(List<ColumnDescription> columnsDescription, String url) {
        Context context = new Context();
        context.setRefContext(new StringAtomicProperty(CsvwKeywords.CSVW_VOCABULARY_URL));

        SchemaDescription schemaDescription = new SchemaDescription();
        schemaDescription.setColumns(new ArrayProperty<>(columnsDescription));
        ObjectProperty<SchemaDescription> tableSchema = new ObjectProperty<>(schemaDescription);

        TableDescription tableDescription = new TableDescription();
        tableDescription.setTableSchema(tableSchema);
        tableDescription.setUrl(new LinkProperty(url));
        tableDescription.setContext(context);
        return tableDescription;
    }

    private boolean containsMultipleSpaces(String value) {
        for (int i = 0; i < value.length() - 1; i++) {
            if (Character.isWhitespace(value.charAt(i)) && Character.isWhitespace(value.charAt(i + 1))) {
                return true;
            }
        }
        return false;
    }
}
