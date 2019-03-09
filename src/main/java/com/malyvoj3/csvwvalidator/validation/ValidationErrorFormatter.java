package com.malyvoj3.csvwvalidator.validation;

public interface ValidationErrorFormatter<T extends ValidationError> {

    String format(T error);

}
