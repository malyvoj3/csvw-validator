package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponse {

    private String id;
    private ValidationStatus validationStatus;
    private String tabularUrl;
    private String metadataUrl;
    private List<ValidationError> validationErrors = new ArrayList<>();
    private Long warningCount;
    private Long errorCount;
    private Long fatalCount;
    private Long totalErrorsCount;

}
