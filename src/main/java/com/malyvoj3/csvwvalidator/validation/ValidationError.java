package com.malyvoj3.csvwvalidator.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    private Severity severity;
    private String message;

    public static ValidationError warn(String message) {
        return new ValidationError(Severity.WARNING, message);
    }

    public static ValidationError error(String message) {
        return new ValidationError(Severity.ERROR, message);
    }

    public static ValidationError fatal(String message) {
        return new ValidationError(Severity.FATAL, message);
    }

}
