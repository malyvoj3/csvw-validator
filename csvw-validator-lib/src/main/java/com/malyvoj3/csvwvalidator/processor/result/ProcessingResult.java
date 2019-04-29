package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProcessingResult implements Result {

    private String tabularUrl;
    private String metadataUrl;
    private ValidationStatus validationStatus;
    private List<LocalizedError> errors;
    private ProcessingSettings settings;
    private long warningCount;
    private long errorCount;
    private long fatalCount;
    private long totalErrorsCount;
    private String usedLanguage;

}
