package com.malyvoj3.csvwvalidator.validation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BatchValidationResult {

    private long filesNum;
    private long passedNum;
    private long warningNum;
    private long errorNum;
    private List<ValidationResult> filesResults = new ArrayList<>();

}
