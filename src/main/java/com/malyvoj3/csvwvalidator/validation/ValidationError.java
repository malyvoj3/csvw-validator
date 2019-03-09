package com.malyvoj3.csvwvalidator.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    private Severity severity;
    private String message;

}
