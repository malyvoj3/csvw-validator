package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.Processor;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.LocalizedError;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class CsvwController {

    private final Processor<ProcessingResult, BatchProcessingResult<ProcessingResult>> processor;

    @Autowired
    public CsvwController(Processor<ProcessingResult, BatchProcessingResult<ProcessingResult>> processor) {
        this.processor = processor;
    }

    @PostMapping(path = "/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationResponse> validate(@RequestBody ValidationRequest request) {
        String metadataUrl = request.getMetadataUrl();
        String tabularUrl = request.getTabularUrl();
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(request.isStrictMode());
        Locale requestLocale = getRequestLocale(request.getLanguage());
        if (requestLocale != null) {
            settings.setLocale(requestLocale);
        }
        ProcessingResult processingResult;

        if (StringUtils.isNotEmpty(metadataUrl) && StringUtils.isNotEmpty(tabularUrl)) {
            processingResult = processor.process(settings, tabularUrl, metadataUrl);
        } else if (StringUtils.isNotEmpty(metadataUrl)) {
            processingResult = processor.processMetadata(settings, metadataUrl);
        } else if (StringUtils.isNotEmpty(tabularUrl)) {
            processingResult = processor.processTabularData(settings, tabularUrl);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(createResponse(processingResult));
    }

    private Locale getRequestLocale(String language) {
        Locale locale;
        if ("en".equals(language)) {
            locale = new Locale("en", "GB");
        } else if ("cs".equals(language)) {
            locale = new Locale("cs", "CZ");
        } else {
            locale = null;
        }
        return locale;
    }

    @PostMapping(path = "/validateBatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BatchValidationResponse> validateBatch(@RequestBody BatchValidationRequest request) {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(request.isStrictMode());
        Locale requestLocale = getRequestLocale(request.getLanguage());
        if (requestLocale != null) {
            settings.setLocale(requestLocale);
        }
        BatchProcessingResult<ProcessingResult> result = processor.process(settings, request.getFilesToProcess());

        List<ValidationResponse> filesResults = result.getFilesResults().stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
        BatchValidationResponse response = BatchValidationResponse.builder()
                .filesCount(result.getFilesCount())
                .passedFilesCount(result.getPassedFilesCount())
                .warningFilesCount(result.getWarningFilesCount())
                .errorFilesCount(result.getErrorFilesCount())
                .filesResults(request.isFilesResults() ? filesResults : null)
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

    private ValidationError createError(LocalizedError error) {
        return new ValidationError(error.getSeverity(), error.getMessage());
    }

}
