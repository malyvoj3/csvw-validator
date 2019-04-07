package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.validation.ValidationError;
import com.malyvoj3.csvwvalidator.validation.ValidationStatus;
import lombok.Data;

import java.util.List;

@Data
public class ProcessingResult {

    private String csvUrl;
    private String metadataUrl;

    private ValidationStatus validationStatus;
    private List<? extends ValidationError> errors;
    private long warningCount;
    private long errorCount;
    private long fatalCount;


}
