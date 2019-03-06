package com.malyvoj3.csvwvalidator.validation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidationError {

    private Severity severity;
    private String message;

}
