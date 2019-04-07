package com.malyvoj3.csvwvalidator.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    private ErrorSeverity severity;
    private String message;

}
