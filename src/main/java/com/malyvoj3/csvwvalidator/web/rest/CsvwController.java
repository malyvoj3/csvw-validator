package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.utils.FileResponse;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import com.malyvoj3.csvwvalidator.validation.JsonParserErrorDefaultFormatter;
import com.malyvoj3.csvwvalidator.validation.ValidationErrorFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class CsvwController {

    @Autowired
    private MetadataParser metadataParser;

    @PostMapping(path = "/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationResponse> validate(@RequestBody ValidationRequest request) {
        ValidationResponse response = new ValidationResponse();
        if (request.getMetadataFilesUrl() != null && request.getMetadataFilesUrl().size() > 0) {
            String fileUrl = request.getMetadataFilesUrl().get(0);
            FileResponse file = FileUtils.downloadFile(request.getMetadataFilesUrl().get(0));
            if (file != null) {
                ValidationErrorFormatter<JsonParserError> formatter = new JsonParserErrorDefaultFormatter();
                MetadataParsingResult result = metadataParser.parseJson(new ByteArrayInputStream(file.getContent()), fileUrl);
                result.getParsingErrors().forEach(error -> response.getValidationErrors().add(formatter.format(error)));
            }
        }
        return ResponseEntity.ok(response);
    }

}
