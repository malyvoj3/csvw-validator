package com.malyvoj3.csvwvalidator.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private ErrorSeverity severity;
    private String message;

}
