package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvwController {

    private final CsvwProcessor csvwProcessor;

    @Autowired
    public CsvwController(CsvwProcessor csvwProcessor) {
        this.csvwProcessor = csvwProcessor;
    }

    @PostMapping(path = "/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationResponse> validate(@RequestBody ValidationRequest request) {
        ValidationResponse response = new ValidationResponse();
        if (request.getMetadataFilesUrl() != null && request.getMetadataFilesUrl().size() > 0) {
            /*String metadataUrl = request.getMetadataFilesUrl().get(0);
            String tabularUrl = request.getTabularFileUrl();
            List<? extends ValidationError> errors = new ArrayList<>();
            if (StringUtils.isNotEmpty(metadataUrl) && StringUtils.isNotEmpty(tabularUrl)) {
                errors = csvwProcessor.processTabularData(tabularUrl, metadataUrl);
            } else if (StringUtils.isNotEmpty(metadataUrl)) {
                errors = csvwProcessor.processMetadata(metadataUrl);
            } else if (StringUtils.isNotEmpty(tabularUrl)) {
                errors = csvwProcessor.processTabularData(tabularUrl);
            }
            response.setValidationErrors(errors.stream()
                    .map(ValidationError::getFormattedMessage)
                    .collect(Collectors.toList()));*/
        }
        return ResponseEntity.ok(response);
    }

}
