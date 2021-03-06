package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.domain.ContentType;
import com.malyvoj3.csvwvalidator.domain.FileResponse;
import com.malyvoj3.csvwvalidator.domain.Severity;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.parser.metadata.ParsingContext;
import com.malyvoj3.csvwvalidator.parser.metadata.TopLevelType;
import com.malyvoj3.csvwvalidator.parser.tabular.Dialect;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularDataParser;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularParsingResult;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.ResultCreator;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.validation.metadata.MetadataValidator;
import com.malyvoj3.csvwvalidator.validation.model.ModelValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class CsvwProcessor implements Processor<ProcessingResult, BatchProcessingResult<ProcessingResult>> {

    private static final String DEFAULT_URL = "http://example.com/";
    private static final String TEXT_CSV_TYPE = "text/csv";
    private static final Set<String> UTF8_ENCODING_NAMES = Stream.of("utf8", "utf-8", "unicode-1-1-utf-8")
            .collect(Collectors.toSet());
    private static final String HEADER_ABSENT = "absent";

    private final TabularDataParser tabularParser;
    private final MetadataParser metadataParser;
    private final SchemaLocator schemaLocator;
    private final MetadataValidator metadataValidator;
    private final ModelValidator modelValidator;
    private final ResultCreator<ProcessingResult, BatchProcessingResult<ProcessingResult>> resultCreator;

    @Override
    public BatchProcessingResult<ProcessingResult> process(ProcessingContext context, List<ProcessingInput> inputs) {
        List<ProcessingResult> processingResults = new ArrayList<>();
        for (ProcessingInput input : inputs) {
            log.info("Processing file {}", input.getTabularUrl());
            ProcessingContext newContext = new ProcessingContext(context.getSettings());
            if (input.getTabularUrl() != null && input.getMetadataUrl() != null) {
                processingResults.add(process(newContext, input.getTabularUrl(), input.getMetadataUrl()));
            } else if (input.getTabularUrl() != null) {
                processingResults.add(processTabularData(newContext, input.getTabularUrl()));
            } else if (input.getMetadataUrl() != null) {
                processingResults.add(processMetadata(newContext, input.getMetadataUrl()));
            }
        }
        return resultCreator.createBatchResult(context, processingResults);
    }

    @Override
    public ProcessingResult processTabularData(ProcessingContext context, String tabularFile, String fileName) {
        Dialect dialect = Dialect.builder().header(true).build();
        TabularParsingResult csvParsingResult;
        csvParsingResult = tabularParser.parse(dialect, fileName, tabularFile, false);
        context.addTabularResult(csvParsingResult);
        return resultCreator.createResult(context, fileName, null);
    }

    @Override
    public ProcessingResult processMetadata(ProcessingContext context, String metadataFile, String fileName) {
        String metadataUrl = DEFAULT_URL + fileName;
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, new ParsingContext(metadataUrl));
        context.addErrors(metadataParsingResult.getErrors());
        if (context.isNotFatal()) {
            context.addErrors(validateMetadata(metadataParsingResult));
        }
        return resultCreator.createResult(context, null, fileName);
    }

    @Override
    public ProcessingResult process(ProcessingContext context, String tabularFile, String tabularFileName,
                                    String metadataFile, String metadataFileName) {
        String tabularUrl = DEFAULT_URL + tabularFileName;
        String metadataUrl = DEFAULT_URL + metadataFileName;
        Dialect dialect = Dialect.builder().header(true).build();
        TabularParsingResult csvParsingResult;
        csvParsingResult = tabularParser.parse(dialect, tabularUrl, tabularFile, false);
        context.addTabularResult(csvParsingResult);
        if (context.isNotFatal()) {
            MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile,
                    new ParsingContext(metadataUrl));
            context.addErrors(metadataParsingResult.getErrors());
            if (context.isNotFatal()) {
                process(context, csvParsingResult, metadataParsingResult);
            }
        }
        return resultCreator.createResult(context, tabularFileName, metadataFileName);
    }

    @Override
    public ProcessingResult processMetadata(ProcessingContext context, String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadMetadataFile(metadataUrl);
        if (metadataResponse != null && metadataResponse.getContent() != null) {
            InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
            MetadataParsingResult metadataParsingResult = metadataParser.parseJson(inputStream,
                    new ParsingContext(metadataResponse.getUrl()));
            if (metadataParsingResult != null) {
                context.addErrors(metadataParsingResult.getErrors());
                if (context.isNotFatal()) {
                    if (TopLevelType.TABLE == metadataParsingResult.getTopLevelType()) {
                        TableDescription tableDesc = (TableDescription) metadataParsingResult.getTopLevelDescription();
                        processMetadata(context, tableDesc);
                    } else {
                        TableGroupDescription tableGrpDesc = (TableGroupDescription) metadataParsingResult.getTopLevelDescription();
                        processMetadata(context, tableGrpDesc);
                    }
                }
            }
        } else {
            context.addError(ValidationError.fatal("error.cantDownloadMetadata", metadataUrl));
        }
        return resultCreator.createResult(context, null, metadataUrl);
    }

    @Override
    public ProcessingResult process(ProcessingContext context, String tabularUrl, String metadataUrl) {
        MetadataParsingResult metadataParsingResult = downloadAndParseMetadata(metadataUrl);
        if (metadataParsingResult != null && metadataParsingResult.getTopLevelDescription() != null) {
            context.addErrors(metadataParsingResult.getValidationErrors());
            if (metadataParsingResult.getTopLevelDescription().describesTabularData(tabularUrl)
                    && context.isNotFatal()) {
                FileResponse tabularResponse = FileUtils.downloadTabularFile(tabularUrl);
                TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
                context.addErrors(validateCsvFileResponse(tabularResponse));
                context.addTabularResult(csvParsingResult);
                if (context.isNotFatal()) {
                    process(context, csvParsingResult, metadataParsingResult);
                }
            } else {
                processMetadata(context, metadataUrl);
            }
        } else {
            context.addError(ValidationError.fatal("error.cantDownloadValidMetadata", metadataUrl));
        }
        return resultCreator.createResult(context, tabularUrl, metadataUrl);
    }

    @Override
    public ProcessingResult processTabularData(ProcessingContext context, String tabularUrl) {
        FileResponse tabularResponse = FileUtils.downloadTabularFile(tabularUrl);
        context.addErrors(validateCsvFileResponse(tabularResponse));
        TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
        context.addTabularResult(csvParsingResult);

        if (context.isNotFatal()) {
            MetadataParsingResult metadataParsingResult = locateMetadata(tabularResponse, csvParsingResult.getTableDescription());
            context.addErrors(metadataParsingResult.getErrors());
            process(context, csvParsingResult, metadataParsingResult);
        }
        return resultCreator.createResult(context, tabularUrl, null);
    }

    private void processMetadata(ProcessingContext context, TableDescription tableDescription) {
        context.addErrors(metadataValidator.validateTable(tableDescription));
        if (context.isNotFatal()) {
            String tabularUrl = tableDescription.getUrl().getValue();
            FileResponse tabularResponse = FileUtils.downloadTabularFile(tabularUrl);
            TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
            context.addErrors(validateCsvFileResponse(tabularResponse));
            context.addTabularResult(csvParsingResult);
            if (context.isNotFatal()) {
                TableDescription embeddedMetadata = csvParsingResult.getTableDescription();
                if (tableDescription.isCompatibleWith(embeddedMetadata)) {
                    context.addErrors(modelValidator.validateTable(csvParsingResult, tableDescription));
                } else {
                    context.addError(ValidationError.fatal("error.notCompatibleTable"));
                }
            }
        }
    }

    private void processMetadata(ProcessingContext context, TableGroupDescription tableGroupDescription) {
        context.addErrors(metadataValidator.validateTableGroup(tableGroupDescription));
        if (context.isNotFatal()) {
            List<TabularParsingResult> csvParsingResults = new ArrayList<>();
            for (TableDescription tableDesc : tableGroupDescription.getTables().getValue()) {
                String tabularUrl = tableDesc.getUrl().getValue();
                FileResponse tabularResponse = FileUtils.downloadTabularFile(tabularUrl);
                TabularParsingResult csvParsingResult = parseCsv(tabularResponse);
                context.addErrors(validateCsvFileResponse(tabularResponse));
                context.addTabularResult(csvParsingResult);
                csvParsingResults.add(csvParsingResult);
                if (!tableDesc.isCompatibleWith(csvParsingResult.getTableDescription())) {
                    context.addError(
                            ValidationError.fatal("error.notCompatibleTableGroup", tableDesc.getUrl().getValue())
                    );
                }
            }
            if (context.isNotFatal()) {
                context.addErrors(modelValidator.validateTableGroup(csvParsingResults, tableGroupDescription));
            }
        }
    }

    private void process(ProcessingContext context,
                         TabularParsingResult csvParsingResult,
                         MetadataParsingResult metadataParsingResult) {
        TableDescription embeddedMetadata = csvParsingResult.getTableDescription();
        if (context.isNotFatal()) {
            context.addErrors(validateMetadata(metadataParsingResult));
            if (context.isNotFatal()) {
                TableDescription tableDesc = getTableDescription(metadataParsingResult, csvParsingResult.getTabularUrl());
                // Embedded metadata has to be compatible with metadata.
                if (tableDesc != null && tableDesc.isCompatibleWith(embeddedMetadata)) {
                    context.addErrors(modelValidator.validateTable(csvParsingResult, tableDesc));
                } else {
                    context.addError(ValidationError.fatal("error.notCompatibleTable"));
                }
            }
        }
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
        if (tabularResponse != null && tabularResponse.getFilePath() != null) {
            String header = Optional.of(tabularResponse)
                    .map(FileResponse::getContentType)
                    .map(ContentType::getHeader)
                    .orElse(null);
            Dialect dialect = Dialect.builder()
                    .header(!HEADER_ABSENT.equals(header))
                    .build();
            csvParsingResult = tabularParser.parse(dialect, tabularResponse.getUrl(), tabularResponse.getFilePath(), tabularResponse.isRemoteFile());
        } else {
            csvParsingResult = new TabularParsingResult();
            csvParsingResult.getParsingErrors().add(ValidationError.fatal("error.cantDownloadCsv"));
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
                    locatingErrors.add(ValidationError.warn("error.invalidSchemaReference", csvFileResponse.getLink().getLink()));
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
                        locatingErrors.add(ValidationError.warn("error.invalidSchemaReference", metadataUrl));
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
            metadataParsingResult.getValidationErrors().add(ValidationError.strictWarn("error.missingSchema"));
        } else {
            metadataParsingResult.getValidationErrors().addAll(locatingErrors);
        }
        return metadataParsingResult;
    }

    private MetadataParsingResult downloadAndParseMetadata(String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadMetadataFile(metadataUrl);
        MetadataParsingResult metadataParsingResult = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
            metadataParsingResult = metadataParser.parseJson(inputStream, new ParsingContext(metadataResponse.getUrl()));
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
                validationErrors.add(ValidationError.strictWarn("error.missingContentType"));
            }
            boolean hasUtfEncoding = UTF8_ENCODING_NAMES.stream()
                    .anyMatch(name -> StringUtils.equalsIgnoreCase(name, contentType.getCharset()));
            if (!hasUtfEncoding) {
                validationErrors.add(ValidationError.strictWarn("error.missingEncoding"));
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
