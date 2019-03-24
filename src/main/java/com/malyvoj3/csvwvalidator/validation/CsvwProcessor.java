package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.parser.csv.CsvParser;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParsingResult;
import com.malyvoj3.csvwvalidator.parser.csv.Dialect;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.utils.ContentType;
import com.malyvoj3.csvwvalidator.utils.FileResponse;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CsvwProcessor {

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

    public void processTabularData(FileResponse fileResponse) {
        List<ValidationError> validationErrors = validateCsvFile(fileResponse);
        Dialect dialect = Dialect.builder()
                .header(!HEADER_ABSENT.equals(fileResponse.getContentType().getHeader()))
                .build();
        CsvParsingResult csvParsingResult = csvParser.parse(dialect, fileResponse.getUrl(), fileResponse.getContent());
        MetadataParsingResult metadataParsingResult = null;

        // Link header metadata locations.
        if (fileResponse.getLink() != null) {
            metadataParsingResult = getMetadataParsingResult(fileResponse, fileResponse.getLink().getLink());
        }

        // Site-wide/default metadata locations.
        if (metadataParsingResult == null) {
            List<String> metadataUrls = siteWideLocator.getMetadataUris(fileResponse.getUrl());
            for (String metadataUrl : metadataUrls) {
                metadataParsingResult = getMetadataParsingResult(fileResponse, metadataUrl);
                if (metadataParsingResult != null) {
                    break;
                }
            }
        }

        // Embedded metadata.
        if (metadataParsingResult == null) {
            validationErrors.add(ValidationError.warn("Missing schema for tabular data." +
                    " Validating will continue using embedded metadata"));
            metadataParsingResult = new MetadataParsingResult();
            metadataParsingResult.setTopLevelDescription(csvParsingResult.getTableDescription());
        }


        //(metadataParsingResult, csvParsingResult);
    }

    private MetadataParsingResult getMetadataParsingResult(FileResponse fileResponse, String metadataUrl) {
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

    private List<ValidationError> validateCsvFile(FileResponse fileResponse) {
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

}
