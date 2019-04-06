package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.domain.ContentType;
import com.malyvoj3.csvwvalidator.domain.FileResponse;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.domain.model.TableGroup;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParser;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParsingResult;
import com.malyvoj3.csvwvalidator.parser.csv.Dialect;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.parser.metadata.TopLevelType;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.validation.metadata.MetadataValidator;
import com.malyvoj3.csvwvalidator.validation.model.ModelValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CsvwProcessor {

    private static final String DEFAULT_URL = "http://example.com/";
    private static final String TEXT_CSV_TYPE = "text/csv";
    private static final Set<String> UTF8_ENCODING_NAMES = Stream.of("utf8", "utf-8", "unicode-1-1-utf-8")
            .collect(Collectors.toSet());
    private static final String HEADER_ABSENT = "absent";

    @Autowired
    private CsvParser csvParser;

    @Autowired
    private MetadataParser metadataParser;

    @Autowired
    private SiteWideLocator siteWideLocator;

    @Autowired
    private AnnotationCreator annotationCreator;

    @Autowired
    private MetadataValidator metadataValidator;

    @Autowired
    private ModelValidator modelValidator;

    /**
     * Processing just upload tabular data file, which means that this file is validated without scheme.
     *
     * @param file
     * @param fileName
     */
    public List<? extends ValidationError> processTabularData(InputStream file, String fileName) {
        Dialect dialect = Dialect.builder().header(true).build();
        List<ValidationError> errors = new ArrayList<>();
        CsvParsingResult csvParsingResult;
        try {
            csvParsingResult = csvParser.parse(dialect, fileName, file);
            errors = csvParsingResult.getParsingErrors();
        } catch (IOException e) {
            errors.add(ValidationError.fatal(String.format("Error during parsing file %s.", fileName)));
        }
        return errors;
    }

    /**
     * Processing just upload metadata file, which means that this file is just validated, that it is valid CSVW metadata.
     *
     * @param file
     * @param fileName
     */
    public List<? extends ValidationError> processMetadata(InputStream file, String fileName) {
        String metadataUrl = DEFAULT_URL + fileName;
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(file, metadataUrl);
        List<ValidationError> processingErrors = new ArrayList<>(metadataParsingResult.getErrors());
        if (hasNoFatalError(processingErrors)) {
            processingErrors.addAll(validateMetadata(metadataParsingResult));
        }
        return metadataParsingResult.getParsingErrors();
    }

    public List<? extends ValidationError> processTabularData(InputStream tabularFile, String tabularFileName, InputStream metadataFile, String metadataFileName) {
        String tabularUrl = DEFAULT_URL + tabularFileName;
        String metadataUrl = DEFAULT_URL + metadataFileName;
        Dialect dialect = Dialect.builder().header(true).build();
        CsvParsingResult csvParsingResult;
        List<ValidationError> processingErrors = new ArrayList<>();
        try {
            csvParsingResult = csvParser.parse(dialect, tabularUrl, tabularFile);
            processingErrors.addAll(csvParsingResult.getParsingErrors());
            MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, metadataUrl);
            if (hasNoFatalError(processingErrors)) {
                processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
            }
        } catch (IOException e) {
            processingErrors.add(ValidationError.fatal(String.format("Cannot parse CSV file '%s'", tabularFileName)));
        }
        return processingErrors;
    }

    /**
     * Processing tabular data URL and uploaded metadata file. URL of metadata file is set to URL of tabular data.
     *
     * @param tabularUrl
     * @param metadataFile
     * @param metadataFileName
     */
    public List<? extends ValidationError> processTabularData(String tabularUrl, InputStream metadataFile, String metadataFileName) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> processingErrors = validateCsvFileResponse(tabularResponse);
        CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
        String metadataUrl = UriUtils.resolveUri(tabularUrl, metadataFileName);
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, metadataUrl);
        processingErrors.addAll(csvParsingResult.getParsingErrors());

        if (hasNoFatalError(processingErrors)) {
            processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
        }
        return processingErrors;
    }

    /**
     * Processing metadata URL, which also downloads tabular data files defined in metadata and validates them.
     *
     * @param metadataUrl
     */
    public List<? extends ValidationError> processMetadata(String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadFile(metadataUrl);
        InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(inputStream, metadataResponse.getUrl());
        List<ValidationError> processingErrors = new ArrayList<>();

        if (metadataParsingResult != null) {
            processingErrors.addAll(metadataParsingResult.getErrors());
            if (hasNoFatalError(processingErrors)) {
                if (TopLevelType.TABLE == metadataParsingResult.getTopLevelType()) {
                    TableDescription tableDesc = (TableDescription) metadataParsingResult.getTopLevelDescription();
                    processingErrors.addAll(processMetadata(tableDesc));
                } else {
                    TableGroupDescription tableGrpDesc = (TableGroupDescription) metadataParsingResult.getTopLevelDescription();
                    processingErrors.addAll(processMetadata(tableGrpDesc));
                }
            }

        } else {
            processingErrors.add(ValidationError.fatal(String.format("Can't download metadata from url '%s'.", metadataUrl)));
        }
        return processingErrors;
    }

    private List<? extends ValidationError> processMetadata(TableDescription tableDescription) {
        List<ValidationError> processingErrors = new ArrayList<>(metadataValidator.validateTable(tableDescription));
        if (hasNoFatalError(processingErrors)) {
            String tabularUrl = tableDescription.getUrl().getValue();
            FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
            CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
            processingErrors.addAll(validateCsvFileResponse(tabularResponse));
            processingErrors.addAll(csvParsingResult.getParsingErrors());
            if (hasNoFatalError(processingErrors)) {
                TableDescription embeddedMetadata = csvParsingResult.getTableDescription();
                if (tableDescription.isCompatibleWith(embeddedMetadata)) {
                    Table annotatedTable = annotationCreator.createAnnotations(csvParsingResult, tableDescription);
                    processingErrors.addAll(modelValidator.validateTable(annotatedTable));
                } else {
                    processingErrors.add(ValidationError.fatal("Embedded metadata are not compatible with metadata."));
                }
            }
        }
        return processingErrors;
    }

    private List<? extends ValidationError> processMetadata(TableGroupDescription tableGroupDescription) {
        List<ValidationError> processingErrors = new ArrayList<>(
                metadataValidator.validateTableGroup(tableGroupDescription)
        );
        if (hasNoFatalError(processingErrors)) {
            List<CsvParsingResult> csvParsingResults = new ArrayList<>();
            for (TableDescription tableDesc : tableGroupDescription.getTables().getValue()) {
                String tabularUrl = tableDesc.getUrl().getValue();
                FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
                CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
                processingErrors.addAll(validateCsvFileResponse(tabularResponse));
                processingErrors.addAll(csvParsingResult.getParsingErrors());
                csvParsingResults.add(csvParsingResult);
            }
            if (hasNoFatalError(processingErrors)) {
                TableGroup annotatedTableGroup = annotationCreator.createAnnotations(csvParsingResults, tableGroupDescription);
                processingErrors.addAll(modelValidator.validateTableGroup(annotatedTableGroup));
            }
        }
        return processingErrors;
    }

    public List<? extends ValidationError> processTabularData(String tabularUrl, String metadataUrl) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> processingErrors = validateCsvFileResponse(tabularResponse);
        CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
        processingErrors.addAll(csvParsingResult.getParsingErrors());
        MetadataParsingResult metadataParsingResult = downloadAndParseMetadata(tabularResponse, metadataUrl);

        if (hasNoFatalError(processingErrors)) {
            if (metadataParsingResult != null) {
                processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
            } else {
                processingErrors.add(ValidationError.fatal(String.format("Can't download valid metadata from url '%s'.", metadataUrl)));
            }
        }
        return processingErrors;
    }

    public List<? extends ValidationError> processTabularData(String tabularUrl) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> processingErrors = validateCsvFileResponse(tabularResponse);
        CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
        processingErrors.addAll(csvParsingResult.getParsingErrors());

        if (hasNoFatalError(processingErrors)) {
            MetadataParsingResult metadataParsingResult = locateMetadata(tabularResponse, csvParsingResult.getTableDescription());
            processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
        }
        return processingErrors;
    }

    private List<ValidationError> process(CsvParsingResult csvParsingResult,
                                          MetadataParsingResult metadataParsingResult) {
        List<ValidationError> processingErrors = new ArrayList<>(metadataParsingResult.getErrors());
        TableDescription embeddedMetadata = csvParsingResult.getTableDescription();
        if (hasNoFatalError(processingErrors)) {
            processingErrors.addAll(validateMetadata(metadataParsingResult));
            if (hasNoFatalError(processingErrors)) {
                TableDescription tableDesc = getTableDescription(metadataParsingResult, csvParsingResult.getCsvUrl());
                // Embedded metadata has to be compatible with metadata.
                if (tableDesc != null && tableDesc.isCompatibleWith(embeddedMetadata)) {
                    Table annotatedTable = annotationCreator.createAnnotations(csvParsingResult, tableDesc);
                    processingErrors.addAll(modelValidator.validateTable(annotatedTable));
                } else {
                    processingErrors.add(ValidationError.fatal("Embedded metadata are not compatible with metadata."));
                }
            }
        }
        return processingErrors;
    }

    private List<? extends ValidationError> validateMetadata(MetadataParsingResult result) {
        List<? extends ValidationError> metadataValidatingErrors;
        if (TopLevelType.TABLE == result.getTopLevelType()) {
            metadataValidatingErrors = metadataValidator.validateTable((TableDescription) result.getTopLevelDescription());
        } else {
            metadataValidatingErrors = metadataValidator.validateTableGroup((TableGroupDescription) result.getTopLevelDescription());
        }
        return metadataValidatingErrors;
    }

    private CsvParsingResult parseCsv(FileResponse tabularResponse) {
        CsvParsingResult csvParsingResult = null;
        if (tabularResponse != null && tabularResponse.getContent() != null) {
            String header = Optional.of(tabularResponse)
                    .map(FileResponse::getContentType)
                    .map(ContentType::getHeader)
                    .orElse(null);
            Dialect dialect = Dialect.builder()
                    .header(!HEADER_ABSENT.equals(header))
                    .build();
            csvParsingResult = csvParser.parse(dialect, tabularResponse.getUrl(), tabularResponse.getContent());
        } else {
            csvParsingResult = new CsvParsingResult();
            csvParsingResult.getParsingErrors().add(ValidationError.fatal("Cannot get CSV file."));
        }
        return csvParsingResult;
    }

    private MetadataParsingResult locateMetadata(FileResponse csvFileResponse, TableDescription embeddedMetadata) {
        MetadataParsingResult metadataParsingResult = null;
        // Link header metadata locations.
        if (csvFileResponse.getLink() != null) {
            metadataParsingResult = downloadAndParseMetadata(csvFileResponse, csvFileResponse.getLink().getLink());
        }

        // Site-wide/default metadata locations.
        if (metadataParsingResult == null) {
            List<String> metadataUrls = siteWideLocator.getMetadataUris(csvFileResponse.getUrl());
            for (String metadataUrl : metadataUrls) {
                metadataParsingResult = downloadAndParseMetadata(csvFileResponse, metadataUrl);
                if (metadataParsingResult != null) {
                    break;
                }
            }
        }

        // Embedded metadata used as metadata.
        if (metadataParsingResult == null) {
            metadataParsingResult = new MetadataParsingResult();
            metadataParsingResult.setTopLevelDescription(embeddedMetadata);
            metadataParsingResult.setTopLevelType(TopLevelType.TABLE);
            metadataParsingResult.getValidationErrors().add(ValidationError.warn("Missing schema for tabular data." +
                    " Validating will continue using embedded metadata"));
        }
        return metadataParsingResult;
    }

    private MetadataParsingResult downloadAndParseMetadata(FileResponse fileResponse, String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadFile(metadataUrl);
        MetadataParsingResult metadataParsingResult = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
            MetadataParsingResult tmp = metadataParser.parseJson(inputStream, metadataResponse.getUrl());
            if (tmp.getTopLevelDescription().describesTabularData(fileResponse.getUrl())) {
                metadataParsingResult = tmp;
            }
        } catch (Exception ex) {
            log.error("Error during getting metadata parsing results.");
        }
        return metadataParsingResult;
    }

    private List<ValidationError> validateCsvFileResponse(FileResponse fileResponse) {
        List<ValidationError> validationErrors = new ArrayList<>();
        if (fileResponse != null && fileResponse.isRemoteFile()) {
            ContentType contentType = fileResponse.getContentType();
            if (!TEXT_CSV_TYPE.equals(contentType.getType())) {
                validationErrors.add(ValidationError.warn("CSV file doesn't have specified 'Content-type' HTTP header."));
            }
            boolean hasUtfEncoding = UTF8_ENCODING_NAMES.stream()
                    .anyMatch(name -> StringUtils.equalsIgnoreCase(name, contentType.getCharset()));
            if (!hasUtfEncoding) {
                validationErrors.add(ValidationError.warn("CSV file doesn't have specified UTF-8 encoding in 'Content-type' HTTP header."));
            }
        }
        return validationErrors;
    }

    private TableDescription getTableDescription(MetadataParsingResult metadataParsingResult, String tabularUrl) {
        TableDescription tableDesc;
        if (TopLevelType.TABLE == metadataParsingResult.getTopLevelType()) {
            tableDesc = (TableDescription) metadataParsingResult.getTopLevelDescription();
        } else {
            // Table group
            TableGroupDescription tableGrpDesc = (TableGroupDescription) metadataParsingResult.getTopLevelDescription();
            tableDesc = findTableDescription(tableGrpDesc, tabularUrl);
        }
        return tableDesc;
    }

    private TableDescription findTableDescription(TableGroupDescription tableGroupDescription, String tabularUrl) {
        return tableGroupDescription.getTables().getValue().stream()
                .filter(desc -> desc.describesTabularData(tabularUrl))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Table group description does not describes tabular data."));
    }

    private boolean hasNoFatalError(List<? extends ValidationError> errors) {
        return errors == null || errors.stream()
                .noneMatch(error -> Severity.FATAL == error.getSeverity());
    }
}
