package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParser;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParsingResult;
import com.malyvoj3.csvwvalidator.parser.csv.Dialect;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.parser.metadata.TopLevelType;
import com.malyvoj3.csvwvalidator.utils.ContentType;
import com.malyvoj3.csvwvalidator.utils.FileResponse;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

//    @Autowired
//    private MetadataValidator metadataValidator;

//    @Autowired
//    private ModelValidator modelValidator;

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
        // TODO metadataValidator.validate() and add errors
        return metadataParsingResult.getParsingErrors();
    }

    public void processTabularData(InputStream tabularFile, String tabularFileName, InputStream metadataFile, String metadataFileName) {
        String tabularUrl = DEFAULT_URL + tabularFileName;
        String metadataUrl = DEFAULT_URL + metadataFileName;
        Dialect dialect = Dialect.builder().header(true).build();
        CsvParsingResult csvParsingResult = new CsvParsingResult();
        try {
            csvParsingResult = csvParser.parse(dialect, tabularUrl, tabularFile);
            MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, metadataUrl);
            // TODO stejne jako processTabularData(String tabularUrl, String metadataUrl)
        } catch (IOException e) {
            // TODO add fatal error
        }
    }

    /**
     * Processing tabular data URL and uploaded metadata file. URL of metadata file is set to URL of tabular data.
     *
     * @param tabularUrl
     * @param metadataFile
     * @param metadataFileName
     */
    public void processTabularData(String tabularUrl, InputStream metadataFile, String metadataFileName) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> validationErrors = validateCsvFileResponse(tabularResponse);
        CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
        String metadataUrl = UriUtils.resolveUri(tabularUrl, metadataFileName);
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(metadataFile, metadataUrl);

        // TODO stejne jako processTabularData(String tabularUrl, String metadataUrl)
    }

    /**
     * Processing metadata URL, which also downloads tabular data files defined in metadata and validates them.
     *
     * @param metadataUrl
     */
    public void processMetadata(String metadataUrl) {
        FileResponse metadataResponse = FileUtils.downloadFile(metadataUrl);
        InputStream inputStream = new ByteArrayInputStream(metadataResponse.getContent());
        MetadataParsingResult metadataParsingResult = metadataParser.parseJson(inputStream, metadataResponse.getUrl());

        // TODO processing
    }

    public void processTabularData(String tabularUrl, String metadataUrl) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> validationErrors = validateCsvFileResponse(tabularResponse);
        CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
        MetadataParsingResult metadataParsingResult = downloadAndParseMetadata(tabularResponse, metadataUrl);
        TableDescription embeddedMetadata = csvParsingResult.getTableDescription();

        if (metadataParsingResult != null) {
            // TODO to same co processTabularData(String tabularUrl)

        } else {
            validationErrors.add(ValidationError.fatal(String.format("Can't download metadata from url '%s'.", metadataUrl)));
        }
    }

    public void processTabularData(String tabularUrl) {
        FileResponse tabularResponse = FileUtils.downloadFile(tabularUrl);
        List<ValidationError> validationErrors = validateCsvFileResponse(tabularResponse);
        CsvParsingResult csvParsingResult = parseCsv(tabularResponse);
        MetadataParsingResult metadataParsingResult = locateMetadata(tabularResponse);
        TableDescription embeddedMetadata = csvParsingResult.getTableDescription();

        // Embedded metadata used as metadata.
        if (metadataParsingResult == null) {
            validationErrors.add(ValidationError.warn("Missing schema for tabular data." +
                    " Validating will continue using embedded metadata"));
            metadataParsingResult = new MetadataParsingResult();
            metadataParsingResult.setTopLevelDescription(csvParsingResult.getTableDescription());
            metadataParsingResult.setTopLevelType(TopLevelType.TABLE);
        }

        // TODO validovat metadata - metadataValidator.validate()
        TableDescription tableDesc = getTableDescription(metadataParsingResult, tabularResponse.getUrl());
        // Embedded metadata has to be compatible with metadata.
        if (tableDesc != null && tableDesc.isCompatibleWith(embeddedMetadata)) {
            Table annotatedTable = annotationCreator.createAnnotations(csvParsingResult.getTable(), tableDesc);
            // TODO validovat model - modelValidator.validate(table)
        } else {
            // TODO not compatible metadata
        }
    }

    private CsvParsingResult parseCsv(FileResponse tabularResponse) {
        Dialect dialect = Dialect.builder()
                .header(!HEADER_ABSENT.equals(tabularResponse.getContentType().getHeader()))
                .build();
        return csvParser.parse(dialect, tabularResponse.getUrl(), tabularResponse.getContent());
    }

    private MetadataParsingResult locateMetadata(FileResponse csvFileResponse) {
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
        ContentType contentType = fileResponse.getContentType();
        if (!TEXT_CSV_TYPE.equals(contentType.getType())) {
            validationErrors.add(ValidationError.warn("CSV file doesn't have specified 'Content-type' HTTP header."));
        }
        boolean hasUtfEncoding = UTF8_ENCODING_NAMES.stream()
                .anyMatch(name -> StringUtils.equalsIgnoreCase(name, contentType.getCharset()));
        if (!hasUtfEncoding) {
            validationErrors.add(ValidationError.warn("CSV file doesn't have specified UTF-8 encoding in 'Content-type' HTTP header."));
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
}
