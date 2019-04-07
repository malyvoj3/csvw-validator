package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.processor.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

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
        String metadataUrl = request.getMetadataUrl();
        String tabularUrl = request.getTabularUrl();
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(request.isStrictMode());
        ProcessingResult processingResult;

        if (StringUtils.isNotEmpty(metadataUrl) && StringUtils.isNotEmpty(tabularUrl)) {
            processingResult = csvwProcessor.processTabularData(settings, tabularUrl, metadataUrl);
        } else if (StringUtils.isNotEmpty(metadataUrl)) {
            processingResult = csvwProcessor.processMetadata(settings, metadataUrl);
        } else if (StringUtils.isNotEmpty(tabularUrl)) {
            processingResult = csvwProcessor.processTabularData(settings, tabularUrl);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(createResponse(processingResult));
    }

    @PostMapping(path = "/validateBatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BatchValidationResponse> validateBatch(@RequestBody BatchValidationRequest request) {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(request.isStrictMode());
        BatchProcessingResult result = csvwProcessor.processTabularData(settings, request.getFilesToProcess());

        BatchValidationResponse response = BatchValidationResponse.builder()
                .filesCount(result.getFilesCount())
                .passedFilesCount(result.getPassedFilesCount())
                .warningFilesCount(result.getWarningFilesCount())
                .errorFilesCount(result.getErrorFilesCount())
                .filesResults(result.getFilesResults().stream()
                        .map(this::createResponse)
                        .collect(Collectors.toList()))
                .build();

        return ResponseEntity.ok(response);
    }

    private ValidationResponse createResponse(ProcessingResult result) {
        return ValidationResponse.builder()
                .tabularUrl(result.getTabularUrl())
                .metadataUrl(result.getMetadataUrl())
                .warningCount(result.getWarningCount())
                .errorCount(result.getErrorCount())
                .fatalCount(result.getFatalCount())
                .totalErrorsCount(result.getTotalErrorsCount())
                .validationStatus(result.getValidationStatus())
                .validationErrors(result.getErrors().stream()
                        .map(this::createError)
                        .collect(Collectors.toList()))
                .build();
    }

    private ValidationError createError(com.malyvoj3.csvwvalidator.validation.ValidationError error) {
        ErrorSeverity severity;
        switch (error.getSeverity()) {
            case STRICT_WARNING:
            case WARNING:
                severity = ErrorSeverity.WARNING;
                break;
            case ERROR:
                severity = ErrorSeverity.ERROR;
                break;
            case FATAL:
                severity = ErrorSeverity.FATAL;
                break;
            default:
                throw new IllegalStateException(String.format("Invalid error severity %s", error.getSeverity()));
        }
        return new ValidationError(severity, error.getFormattedMessage());
    }

}
