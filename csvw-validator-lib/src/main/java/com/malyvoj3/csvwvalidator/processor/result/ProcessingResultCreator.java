package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.domain.Severity;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.Translator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ProcessingResultCreator implements ResultCreator<ProcessingResult, BatchProcessingResult> {

    private final Translator translator;

    @Override
    public ProcessingResult createResult(ProcessingSettings settings, List<? extends ValidationError> errors, String tabularUrl, String metadataUrl) {
        System.out.println("GGG: creating processing resuslt");
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

        List<LocalizedError> localizedErrors = validationErrors.stream()
                .map(error -> {
                    ValidationError msg = error.getFormattedMessage();
                    return new LocalizedError(msg.getSeverity().name(),
                            translator.getTranslation(msg.getMessageCode(), settings.getLocale(), msg.getParams()));
                }).collect(Collectors.toList());

        return ProcessingResult.builder()
                .tabularUrl(tabularUrl)
                .metadataUrl(metadataUrl)
                .errors(localizedErrors)
                .warningCount(warningCount)
                .errorCount(errorCount)
                .fatalCount(fatalCount)
                .totalErrorsCount(validationErrors.size())
                .validationStatus(status)
                .settings(settings)
                .usedLanguage(settings.getLocale().getLanguage())
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
