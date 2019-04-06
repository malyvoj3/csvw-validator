package com.malyvoj3.csvwvalidator.validation;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResult {

    private String csvUrl;
    private String metadataUrl;

    private ValidationStatus validationStatus;
    private List<? extends ValidationError> errors;
    private long warningCount;
    private long errorCount;
    private long fatalCount;


}
