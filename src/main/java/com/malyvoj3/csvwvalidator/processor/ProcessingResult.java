package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.validation.ValidationError;
import com.malyvoj3.csvwvalidator.validation.ValidationStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProcessingResult {

    private String tabularUrl;
    private String metadataUrl;
    private ValidationStatus validationStatus;
    private List<? extends ValidationError> errors;
    private ProcessingSettings settings;
    private long warningCount;
    private long errorCount;
    private long fatalCount;
    private long totalErrorsCount;

}
