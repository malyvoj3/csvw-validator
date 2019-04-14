package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationResponse {

    private ValidationStatus validationStatus;
    private String tabularUrl;
    private String metadataUrl;
    private List<ValidationError> validationErrors;
    private long warningCount;
    private long errorCount;
    private long fatalCount;
    private long totalErrorsCount;

}
