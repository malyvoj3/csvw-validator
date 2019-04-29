package com.malyvoj3.csvwvalidator.domain;

import lombok.Data;

@Data
public class ValidationError {

    private final Severity severity;
    private final String messageCode;
    private final Object[] params;

    public ValidationError(Severity severity, String messageCode) {
        this.severity = severity;
        this.messageCode = messageCode;
        this.params = null;
    }

    public ValidationError(Severity severity, String messageCode, Object[] params) {
        this.severity = severity;
        this.messageCode = messageCode;
        this.params = params;
    }

    public static ValidationError strictWarn(String messageCode, Object... args) {
        return new ValidationError(Severity.STRICT_WARNING, messageCode, args);
    }

    public static ValidationError warn(String messageCode, Object... args) {
        return new ValidationError(Severity.WARNING, messageCode, args);
    }

    public static ValidationError error(String messageCode, Object... args) {
        return new ValidationError(Severity.ERROR, messageCode, args);
    }

    public static ValidationError fatal(String messageCode, Object... args) {
        return new ValidationError(Severity.FATAL, messageCode, args);
    }

    public static ValidationError strictWarn(String messageCode) {
        return new ValidationError(Severity.STRICT_WARNING, messageCode);
    }

    public static ValidationError warn(String messageCode) {
        return new ValidationError(Severity.WARNING, messageCode);
    }

    public static ValidationError error(String messageCode) {
        return new ValidationError(Severity.ERROR, messageCode);
    }

    public static ValidationError fatal(String messageCode) {
        return new ValidationError(Severity.FATAL, messageCode);
    }

    public ValidationError getFormattedMessage() {
        return this;
    }

}
