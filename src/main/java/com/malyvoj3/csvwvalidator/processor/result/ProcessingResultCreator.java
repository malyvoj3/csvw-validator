package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.validation.Severity;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import com.malyvoj3.csvwvalidator.validation.ValidationStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ProcessingResultCreator implements ResultCreator<ProcessingResult, BatchProcessingResult> {

    @Override
    public ProcessingResult createResult(ProcessingSettings settings, List<? extends ValidationError> errors, String tabularUrl, String metadataUrl) {
        List<? extends ValidationError> validationErrors;
        if (!settings.isStrictMode()) {
            validationErrors = errors.stream()
                    .filter(error -> error.getSeverity() != Severity.STRICT_WARNING)
                    .collect(Collectors.toList());
        } else {
            validationErrors = errors;
        }
        long warningCount = 0L;
        long errorCount = 0L;
        long fatalCount = 0L;
        for (ValidationError error : validationErrors) {
            switch (error.getSeverity()) {
                case STRICT_WARNING:
                    warningCount++;
                    break;
                case WARNING:
                    warningCount++;
                    break;
                case ERROR:
                    errorCount++;
                    break;
                case FATAL:
                    fatalCount++;
                    break;
                default:
                    break;
            }
        }
        ValidationStatus status = ValidationStatus.PASSED;
        if (errorCount > 0 || fatalCount > 0) {
            status = ValidationStatus.ERROR;
        } else if (warningCount > 0) {
            status = ValidationStatus.WARNING;
        }

        return ProcessingResult.builder()
                .tabularUrl(tabularUrl)
                .metadataUrl(metadataUrl)
                .errors(validationErrors)
                .warningCount(warningCount)
                .errorCount(errorCount)
                .fatalCount(fatalCount)
                .totalErrorsCount(validationErrors.size())
                .validationStatus(status)
                .settings(settings)
                .build();
    }

    @Override
    public BatchProcessingResult createBatchResult(ProcessingSettings settings, List<ProcessingResult> processingResults) {
        long passedCount = 0L;
        long warningCount = 0L;
        long errorCount = 0L;
        for (ProcessingResult result : processingResults) {
            switch (result.getValidationStatus()) {
                case PASSED:
                    passedCount++;
                    break;
                case WARNING:
                    warningCount++;
                    break;
                case ERROR:
                    errorCount++;
                    break;
                default:
                    break;
            }
        }

        return BatchProcessingResult.builder()
                .filesCount(processingResults.size())
                .passedFilesCount(passedCount)
                .warningFilesCount(warningCount)
                .errorFilesCount(errorCount)
                .filesResults(processingResults)
                .build();
    }

}
