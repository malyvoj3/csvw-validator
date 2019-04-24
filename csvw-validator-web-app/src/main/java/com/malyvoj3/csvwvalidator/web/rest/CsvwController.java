package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CsvwController {

    private final CsvwProcessor csvwProcessor;

    @Autowired
    public CsvwController(CsvwProcessor csvwProcessor) {
        this.csvwProcessor = csvwProcessor;
    }

    private boolean isConnected(HttpServletResponse servletResponse) {
        try {
            servletResponse.getOutputStream().println("data");
            servletResponse.flushBuffer();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @PostMapping(path = "/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationResponse> validate(HttpServletResponse servletResponse, @RequestBody ValidationRequest request) {
        new ControlSubThread(700, servletResponse) {
            @Override
            public void run() {
                super.run();
            }
        };
        final ProcessingResult[] processingResult = new ProcessingResult[1];
        new Thread(() -> {
            String metadataUrl = request.getMetadataUrl();
            String tabularUrl = request.getTabularUrl();
            ProcessingSettings settings = new ProcessingSettings();
            settings.setStrictMode(request.isStrictMode());

            if (StringUtils.isNotEmpty(metadataUrl) && StringUtils.isNotEmpty(tabularUrl)) {
                processingResult[0] = csvwProcessor.processTabularData(settings, tabularUrl, metadataUrl);
            } else if (StringUtils.isNotEmpty(metadataUrl)) {
                processingResult[0] = csvwProcessor.processMetadata(settings, metadataUrl);
            } else if (StringUtils.isNotEmpty(tabularUrl)) {
                processingResult[0] = csvwProcessor.processTabularData(settings, tabularUrl);
            } else {
                entity[0] =
            }
            if (processingResult != null) {
                entity[0] = ResponseEntity.ok(createResponse(processingResult));
            }
        });
        if (processingResult[0] != null) {
            return ResponseEntity.ok(createResponse(processingResult[1]));
        } else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(path = "/validateBatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BatchValidationResponse> validateBatch(@RequestBody BatchValidationRequest request) {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(request.isStrictMode());
        BatchProcessingResult result = csvwProcessor.processTabularData(settings, request.getFilesToProcess());

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

    private ValidationError createError(com.malyvoj3.csvwvalidator.domain.ValidationError error) {
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
