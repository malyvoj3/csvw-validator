package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.domain.ContentType;
import com.malyvoj3.csvwvalidator.domain.FileResponse;
import com.malyvoj3.csvwvalidator.domain.Severity;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.domain.model.TableGroup;
import com.malyvoj3.csvwvalidator.parser.csv.Dialect;
import com.malyvoj3.csvwvalidator.parser.csv.TabularDataParser;
import com.malyvoj3.csvwvalidator.parser.csv.TabularParsingResult;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.parser.metadata.TopLevelType;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.ResultCreator;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.validation.metadata.MetadataValidator;
import com.malyvoj3.csvwvalidator.validation.model.ModelValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
@RequiredArgsConstructor
public class CsvwProcessor implements Processor<ProcessingResult, BatchProcessingResult> {

    private static final String DEFAULT_URL = "http://example.com/";
    private static final String TEXT_CSV_TYPE = "text/csv";
    private static final Set<String> UTF8_ENCODING_NAMES = Stream.of("utf8", "utf-8", "unicode-1-1-utf-8")
            .collect(Collectors.toSet());
    private static final String HEADER_ABSENT = "absent";

    private final TabularDataParser tabularParser;
    private final MetadataParser metadataParser;
    private final SchemaLocator schemaLocator;
    private final AnnotationCreator annotationCreator;
    private final MetadataValidator metadataValidator;
    private final ModelValidator modelValidator;
    private final ResultCreator<ProcessingResult, BatchProcessingResult> resultCreator;

    @Override
    public BatchProcessingResult processTabularData(ProcessingSettings settings, List<ProcessingInput> inputs) {
        List<ProcessingResult> processingResults = new ArrayList<>();
        for (ProcessingInput input : inputs) {
            log.info("Processing file {}", input.getTabularUrl());
            if (input.getTabularUrl() != null && input.getMetadataUrl() != null) {
                processingResults.add(processTabularData(settings, input.getTabularUrl(), input.getMetadataUrl()));
            } else if (input.getTabularUrl() != null) {
                processingResults.add(processTabularData(settings, input.getTabularUrl()));
            } else if (input.getMetadataUrl() != null) {
                processingResults.add(processMetadata(settings, input.getMetadataUrl()));
            }
        }
        return resultCreator.createBatchResult(settings, processingResults);
    }

    /**
     * Processing just upload tabular data file, which means that this file is validated without scheme.
     *
     * @param file
     * @param fileName
     */
    @Override
    public ProcessingResult processTabularData(ProcessingSettings settings, InputStream file, String fileName) {
        Dialect dialect = Dialect.builder().header(true).build();
        List<ValidationError> errors = new ArrayList<>();
        TabularParsingResult csvParsingResult;
        try {
            csvParsingResult = tabularParser.parse(dialect, fileName, file);
            errors = csvParsingResult.getParsingErrors();
        } catch (IOException e) {
            errors.add(ValidationError.fatal("Error during parsing file %s.", fileName));
        }
        return resultCreator.createResult(settings, errors, fileName, null);
    }

    /**
     * Processing just upload metadata file, which means that this file is just validated, that it is valid CSVW metadata.
     *
     * @param file
     * @param fileName
     */
    @Override
    public ProcessingResult processMetadata(ProcessingSettings settings, InputStream file, String fileName) {
        String metadataUrl = DEFAULT_URL + fileName;
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(file, metadataUrl);
        List<ValidationError> processingErrors = new ArrayList<>(metadataParsingResult.getErrors());
        if (hasNoFatalError(processingErrors)) {
            processingErrors.addAll(validateMetadata(metadataParsingResult));
        }
        return resultCreator.createResult(settings, metadataParsingResult.getParsingErrors(), null, fileName);
    }

    @Override
    public ProcessingResult processTabularData(ProcessingSettings settings, InputStream tabularFile, String tabularFileName, InputStream metadataFile, String metadataFileName) {
        String tabularUrl = DEFAULT_URL + tabularFileName;
        String metadataUrl = DEFAULT_URL + metadataFileName;
        Dialect dialect = Dialect.builder().header(true).build();
        TabularParsingResult csvParsingResult;
        List<ValidationError> processingErrors = new ArrayList<>();
        try {
            csvParsingResult = tabularParser.parse(dialect, tabularUrl, tabularFile);
            processingErrors.addAll(csvParsingResult.getParsingErrors());
            MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, metadataUrl);
            if (hasNoFatalError(processingErrors)) {
                processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
            }
        } catch (IOException e) {
            processingErrors.add(ValidationError.fatal("Cannot parse CSV file '%s'", tabularFileName));
        }
        return resultCreator.createResult(settings, processingErrors, tabularFileName, metadataFileName);
    }

    /**
     * Processing tabular data URL and uploaded metadata file. URL of metadata file is set to URL of tabular data.
     *
     * @param tabularUrl
     * @param metadataFile
     * @param metadataFileName
     */
    @Override
    public ProcessingResult processTabularData(ProcessingSettings settings, String tabularUrl, InputStream metadataFile, String metadataFileName) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> processingErrors = validateCsvFileResponse(tabularResponse);
        TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
        String metadataUrl = UriUtils.resolveUri(tabularUrl, metadataFileName);
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, metadataUrl);
        processingErrors.addAll(csvParsingResult.getParsingErrors());

        if (hasNoFatalError(processingErrors)) {
            processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
        }
        return resultCreator.createResult(settings, processingErrors, tabularUrl, metadataUrl);
    }

    /**
     * Processing metadata URL, which also downloads tabular data files defined in metadata and validates them.
     *
     * @param metadataUrl
     */
    @Override
    public ProcessingResult processMetadata(ProcessingSettings settings, String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadFile(metadataUrl);
        List<ValidationError> processingErrors = new ArrayList<>();
        if (metadataResponse != null && metadataResponse.getContent() != null) {
            InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
            MetadataParsingResult metadataParsingResult = metadataParser.parseJson(inputStream, metadataResponse.getUrl());
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

            }
        } else {
            processingErrors.add(ValidationError.fatal("Can't download metadata from url '%s'.", metadataUrl));
        }
        return resultCreator.createResult(settings, processingErrors, null, metadataUrl);
    }

    @Override
    public ProcessingResult processTabularData(ProcessingSettings settings, String tabularUrl, String metadataUrl) {
        MetadataParsingResult metadataParsingResult = downloadAndParseMetadata(metadataUrl);
        List<ValidationError> processingErrors = new ArrayList<>();
        if (metadataParsingResult != null && metadataParsingResult.getTopLevelDescription() != null) {
            processingErrors.addAll(metadataParsingResult.getValidationErrors());
            if (metadataParsingResult.getTopLevelDescription().describesTabularData(tabularUrl)
                    && hasNoFatalError(processingErrors)) {
                FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
                TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
                processingErrors.addAll(csvParsingResult.getParsingErrors());
                processingErrors.addAll(validateCsvFileResponse(tabularResponse));
                if (hasNoFatalError(processingErrors)) {
                    processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
                }
            } else {
                processMetadata(settings, metadataUrl);
            }
        } else {
            processingErrors.add(ValidationError.fatal("Can't download valid metadata from url '%s'.", metadataUrl));
        }
        return resultCreator.createResult(settings, processingErrors, tabularUrl, metadataUrl);
    }

    @Override
    public ProcessingResult processTabularData(ProcessingSettings settings, String tabularUrl) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> processingErrors = validateCsvFileResponse(tabularResponse);
        TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
        processingErrors.addAll(csvParsingResult.getParsingErrors());

        if (hasNoFatalError(processingErrors)) {
            MetadataParsingResult metadataParsingResult = locateMetadata(tabularResponse, csvParsingResult.getTableDescription());
            processingErrors.addAll(process(csvParsingResult, metadataParsingResult));
        }
        return resultCreator.createResult(settings, processingErrors, tabularUrl, null);
    }

    private List<? extends ValidationError> processMetadata(TableDescription tableDescription) {
        List<ValidationError> processingErrors = new ArrayList<>(metadataValidator.validateTable(tableDescription));
        if (hasNoFatalError(processingErrors)) {
            String tabularUrl = tableDescription.getUrl().getValue();
            FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
            TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
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
            List<TabularParsingResult> csvParsingResults = new ArrayList<>();
            for (TableDescription tableDesc : tableGroupDescription.getTables().getValue()) {
                String tabularUrl = tableDesc.getUrl().getValue();
                FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
                TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
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

    private List<ValidationError> process(TabularParsingResult csvParsingResult,
                                          MetadataParsingResult metadataParsingResult) {
        List<ValidationError> processingErrors = new ArrayList<>(metadataParsingResult.getErrors());
        TableDescription embeddedMetadata = csvParsingResult.getTableDescription();
        if (hasNoFatalError(processingErrors)) {
            processingErrors.addAll(validateMetadata(metadataParsingResult));
            if (hasNoFatalError(processingErrors)) {
                TableDescription tableDesc = getTableDescription(metadataParsingResult, csvParsingResult.getTabularUrl());
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

    private TabularParsingResult parseCsv(FileResponse tabularResponse) {
        TabularParsingResult csvParsingResult = null;
        if (tabularResponse != null && tabularResponse.getContent() != null) {
            String header = Optional.of(tabularResponse)
                    .map(FileResponse::getContentType)
                    .map(ContentType::getHeader)
                    .orElse(null);
            Dialect dialect = Dialect.builder()
                    .header(!HEADER_ABSENT.equals(header))
                    .build();
            csvParsingResult = tabularParser.parse(dialect, tabularResponse.getUrl(), tabularResponse.getContent());
        } else {
            csvParsingResult = new TabularParsingResult();
            csvParsingResult.getParsingErrors().add(ValidationError.fatal("Cannot download CSV file."));
        }
        return csvParsingResult;
    }

    private MetadataParsingResult locateMetadata(FileResponse csvFileResponse, TableDescription embeddedMetadata) {
        List<ValidationError> locatingErrors = new ArrayList<>();
        MetadataParsingResult metadataParsingResult = null;
        MetadataParsingResult tmpResult = null;
        // Link header metadata locations.
        if (csvFileResponse.getLink() != null) {
            tmpResult = downloadAndParseMetadata(csvFileResponse.getLink().getLink());
            if (tmpResult != null && tmpResult.getTopLevelDescription() != null) {
                if (tmpResult.getTopLevelDescription().describesTabularData(csvFileResponse.getUrl())) {
                    metadataParsingResult = tmpResult;
                } else {
                    locatingErrors.add(ValidationError.warn("Schema '%s' does not explicitly include a" +
                            " reference to the requested tabular data file.", csvFileResponse.getLink().getLink()));
                }
            }
        }

        // Site-wide/default metadata locations.
        if (metadataParsingResult == null) {
            List<String> metadataUrls = schemaLocator.getMetadataUris(csvFileResponse.getUrl());
            for (String metadataUrl : metadataUrls) {
                tmpResult = downloadAndParseMetadata(metadataUrl);
                if (tmpResult != null && tmpResult.getTopLevelDescription() != null) {
                    if (tmpResult.getTopLevelDescription().describesTabularData(csvFileResponse.getUrl())) {
                        metadataParsingResult = tmpResult;
                        break;
                    } else {
                        locatingErrors.add(ValidationError.warn("Schema '%s' does not explicitly include a" +
                                " reference to the requested tabular data file.", metadataUrl));
                    }
                }
            }
        }

        // Embedded metadata used as metadata.
        if (metadataParsingResult == null) {
            metadataParsingResult = new MetadataParsingResult();
            metadataParsingResult.setTopLevelDescription(embeddedMetadata);
            metadataParsingResult.setTopLevelType(TopLevelType.TABLE);
            metadataParsingResult.setValidationErrors(locatingErrors);
            metadataParsingResult.getValidationErrors().add(ValidationError.strictWarn("Missing schema for tabular data." +
                    " Validating will continue using embedded metadata"));
        } else {
            metadataParsingResult.getValidationErrors().addAll(locatingErrors);
        }
        return metadataParsingResult;
    }

    private MetadataParsingResult downloadAndParseMetadata(String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadFile(metadataUrl);
        MetadataParsingResult metadataParsingResult = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
            metadataParsingResult = metadataParser.parseJson(inputStream, metadataResponse.getUrl());
        } catch (Exception ex) {
            log.info("Error during downloading metadata parsing results.");
        }
        return metadataParsingResult;
    }

    private List<ValidationError> validateCsvFileResponse(FileResponse fileResponse) {
        List<ValidationError> validationErrors = new ArrayList<>();
        if (fileResponse != null && fileResponse.isRemoteFile()) {
            ContentType contentType = fileResponse.getContentType();
            if (!TEXT_CSV_TYPE.equals(contentType.getType())) {
                validationErrors.add(ValidationError.strictWarn("CSV file doesn't have specified 'Content-type' HTTP header."));
            }
            boolean hasUtfEncoding = UTF8_ENCODING_NAMES.stream()
                    .anyMatch(name -> StringUtils.equalsIgnoreCase(name, contentType.getCharset()));
            if (!hasUtfEncoding) {
                validationErrors.add(ValidationError.strictWarn("CSV file doesn't have specified UTF-8 encoding in 'Content-type' HTTP header."));
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
