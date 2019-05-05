package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.domain.Severity;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import com.malyvoj3.csvwvalidator.processor.ProcessingContext;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.Translator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ProcessingResultCreator implements ResultCreator<ProcessingResult, BatchProcessingResult<ProcessingResult>> {

    private final Translator translator;

    @Override
    public ProcessingResult createResult(ProcessingContext context, String tabularUrl, String metadataUrl) {
        List<? extends ValidationError> validationErrors;
        ProcessingSettings settings = context.getSettings();
        List<ValidationError> errors = context.getErrors();
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

        List<LocalizedError> localizedErrors = validationErrors.stream()
                .map(error -> {
                    ValidationError msg = error.getFormattedMessage();
                    return new LocalizedError(msg.getSeverity().name(),
                            translator.getTranslation(msg.getMessageCode(), settings.getLocale(), msg.getParams()));
                }).collect(Collectors.toList());

        ProcessingResult result = new ProcessingResult();
        result.setTabularUrl(tabularUrl);
        result.setMetadataUrl(metadataUrl);
        result.setErrors(localizedErrors);
        result.setWarningCount(warningCount);
        result.setErrorCount(errorCount);
        result.setFatalCount(fatalCount);
        result.setTotalErrorsCount(validationErrors.size());
        result.setValidationStatus(status);
        result.setStrictMode(settings.isStrictMode());
        result.setUsedLanguage(settings.getLocale().getLanguage());
        return result;
    }

    @Override
    public BatchProcessingResult<ProcessingResult> createBatchResult(ProcessingContext context, List<ProcessingResult> processingResults) {
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
