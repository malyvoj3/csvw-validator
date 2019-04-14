package com.malyvoj3.csvwvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    private Severity severity;
    private String message;

    public static ValidationError strictWarn(String message, Object... args) {
        return ValidationError.strictWarn(String.format(message, args));
    }

    public static ValidationError warn(String message, Object... args) {
        return ValidationError.warn(String.format(message, args));
    }

    public static ValidationError error(String message, Object... args) {
        return ValidationError.error(String.format(message, args));
    }

    public static ValidationError fatal(String message, Object... args) {
        return ValidationError.fatal(String.format(message, args));
    }

    public static ValidationError strictWarn(String message) {
        return new ValidationError(Severity.STRICT_WARNING, message);
    }

    public static ValidationError warn(String message) {
        return new ValidationError(Severity.WARNING, message);
    }

    public static ValidationError error(String message) {
        return new ValidationError(Severity.ERROR, message);
    }

    public static ValidationError fatal(String message) {
        return new ValidationError(Severity.FATAL, message);
    }

    public String getFormattedMessage() {
        return message;
    }

}
