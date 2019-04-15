package com.malyvoj3.csvwvalidator.parser.tabular;


import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import com.malyvoj3.csvwvalidator.domain.model.Cell;
import com.malyvoj3.csvwvalidator.domain.model.Column;
import com.malyvoj3.csvwvalidator.domain.model.Row;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Slf4j
public class CsvParser implements TabularDataParser {

    private static final String FIELD_DELIMITER_DEFAULT = ",";
    private static final String LINE_SEPARATOR_DEFAULT = "\r\n";
    private static final char QUOTE_CHAR_DEFAULT = '"';
    private static final char QUOTE_ESCAPE_CHAR_DEFAULT = '"';

    @Override
    public TabularParsingResult parse(Dialect dialect, String url) throws URISyntaxException, IOException {
        byte[] fileArray = IOUtils.toByteArray(new URI(url));
        return parse(dialect, url, fileArray);
    }

    @Override
    public TabularParsingResult parse(Dialect dialect, String url, InputStream inputStream) throws IOException {
        byte[] fileArray = IOUtils.toByteArray(inputStream);
        return parse(dialect, url, fileArray);
    }

    @Override
    public TabularParsingResult parse(Dialect dialect, String url, byte[] file) {
        Table table = new Table();
        table.setUrl(url);
        List<Column> columns = new ArrayList<>();
        List<ColumnDescription> columnDescriptions = new ArrayList<>();
        TableDescription tableDescription = null;
        List<ValidationError> parsingErrors = new ArrayList<>();

        if (file.length > 0) {
            try {
                Reader reader = new InputStreamReader(new ByteArrayInputStream(file));
                if (!FileUtils.isUtf8(file)) {
                    parsingErrors.add(ValidationError.strictWarn("Invalid encoding: file has not 'UTF-8' encoding."));
                }

                com.univocity.parsers.csv.CsvParser csvParser = new com.univocity.parsers.csv.CsvParser(defaultSettings(dialect));
                List<String[]> records = csvParser.parseAll(reader);
                createColumns(csvParser, table, columns, columnDescriptions);
                parsingErrors.addAll(createRows(records, table, columns, dialect.isHeader()));
                parsingErrors.addAll(validateColumns(columns));
                parsingErrors.addAll(validateCsvFormat(csvParser.getDetectedFormat()));
                table.setColumns(columns);
                tableDescription = createTableDescription(columnDescriptions, url);
            } catch (CsvFormatException ex) {
                log.error("Invalid CSF file format. Stop parsing.");
                parsingErrors.add(ex.getValidationError());
            } catch (Exception ex) {
                log.error(String.format("Error during CSV parsing of file '%s'.", url), ex);
                parsingErrors.add(ValidationError.fatal("File '%s' is not valid CSV file.", url));
            }
        } else {
            parsingErrors.add(ValidationError.fatal("The CSV file is empty."));
        }

        return new TabularParsingResult(url, parsingErrors, table, tableDescription);
    }

    private void createColumns(com.univocity.parsers.csv.CsvParser parser, Table table, List<Column> columns, List<ColumnDescription> columnDescriptions) {
        int columnNumber = 1;
        for (String header : parser.getContext().headers()) {
            List<String> headerTitles = new ArrayList<>();
            headerTitles.add(header);
            Map<String, List<String>> titles = new HashMap<>();
            titles.put(CsvwKeywords.NATURAL_LANGUAGE_CODE, headerTitles);
            columns.add(Column.builder()
                    .titles(titles)
                    .name(header)
                    .cells(new ArrayList<>())
                    .number(columnNumber)
                    .table(table).build());
            columnDescriptions.add(createColumnDescription(header));
            columnNumber++;
        }
    }

    private List<ValidationError> createRows(List<String[]> records, Table table, List<Column> columns, boolean hasHeader) throws CsvFormatException {
        List<ValidationError> parsingErrors = new ArrayList<>();
        int rowNumber = hasHeader ? 2 : 1;
        int columnLength = columns.size();
        for (String[] record : records) {
            if (record.length > 0) {
                Row row = new Row();
                row.setNumber(rowNumber);
                parsingErrors.addAll(validateRowLength(record.length, rowNumber, columnLength));
                for (int i = 0; i < record.length; i++) {
                    String value = record[i];
                    parsingErrors.addAll(validateValue(value, rowNumber, i));
                    Column cellColumn = i < columns.size() ? columns.get(i) : null;
                    Cell cell = Cell.builder()
                            .column(cellColumn)
                            .row(row)
                            .table(table)
                            .stringValue(value)
                            .errors(new ArrayList<>())
                            .build();
                    row.getCells().add(cell);
                    if (cellColumn != null) {
                        cellColumn.getCells().add(cell);
                    }
                }
                table.getRows().add(row);
            } else {
                parsingErrors.add(ValidationError.strictWarn("Empty row number %d.", rowNumber));
            }
            rowNumber++;
        }
        return parsingErrors;
    }

    private List<ValidationError> validateRowLength(int recordLength, int rowNumber, int columnLength) {
        List<ValidationError> errors = new ArrayList<>();
        if (recordLength > columnLength) {
            errors.add(ValidationError.fatal(
                    "Row %d has more cells than columns." +
                            " Possible problems: wrong quote of cell, wrong escape of quote character, extra delimiter/value.", rowNumber)
            );
        } else if (recordLength < columnLength) {
            errors.add(ValidationError.fatal(
                    "Row %d has fewer cells than columns." +
                            " Possible problems: missing header, wrong quoting of cell, missing delimiters/values.", rowNumber)
            );
        }
        return errors;
    }

    private List<ValidationError> validateColumns(List<Column> columns) {
        List<ValidationError> errors = new ArrayList<>();
        for (Column column : columns) {
            long notBlankNum = column.getCells().stream()
                    .map(Cell::getStringValue)
                    .filter(StringUtils::isNotBlank)
                    .count();
            if (notBlankNum == 0) {
                errors.add(ValidationError.strictWarn("Column '%s' is empty column.", column.getName()));
            }
        }
        return errors;
    }

    private List<ValidationError> validateValue(String value, int rowNumber, int columnNumber) {
        List<ValidationError> errors = Collections.emptyList();
        if (value == null) {
            errors = Collections.emptyList();
        } else if (StringUtils.isWhitespace(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "Value in row %d column %d is just whitespace.", rowNumber, columnNumber
            ));
        } else if ("null".equals(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "Value in row %d column %d is equal to 'null', should be empty string.", rowNumber, columnNumber
            ));
        } else if (containsLeadingSpaces(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "Value in row %d column %d has leading spaces.", rowNumber, columnNumber
            ));
        } else if (containsTrailingSpaces(value)) {
            errors = Collections.singletonList(ValidationError.strictWarn(
                    "Value in row %d column %d has trailing spaces.", rowNumber, columnNumber
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
                    invalidMsg("line separator", escapeLineSeparator(detectedFormat.getLineSeparatorString()), escapeLineSeparator(LINE_SEPARATOR_DEFAULT))
            ));
        }
        if (!FIELD_DELIMITER_DEFAULT.equals(detectedFormat.getDelimiterString())) {
            errorList.add(ValidationError.strictWarn(
                    invalidMsg("field delimiter", detectedFormat.getDelimiter() == '\t' ? "\\t" : detectedFormat.getDelimiterString(), FIELD_DELIMITER_DEFAULT)
            ));
        }
        if (QUOTE_CHAR_DEFAULT != detectedFormat.getQuote()) {
            errorList.add(ValidationError.strictWarn(
                    invalidMsg("quote character", String.valueOf(detectedFormat.getQuote()), String.valueOf(QUOTE_CHAR_DEFAULT))
            ));
        }
        if (QUOTE_ESCAPE_CHAR_DEFAULT != detectedFormat.getQuoteEscape()) {
            errorList.add(ValidationError.strictWarn(
                    invalidMsg("quote escape character", String.valueOf(detectedFormat.getQuoteEscape()), String.valueOf(QUOTE_CHAR_DEFAULT))
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

    private static String invalidMsg(String fieldName, String invalidValue, String defaultValue) {
        return String.format("Invalid %s: it looks like %s is '%s' instead '%s'.",
                fieldName, fieldName, invalidValue, defaultValue);
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
