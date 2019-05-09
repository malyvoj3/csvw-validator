package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;

import java.util.List;

/**
 * Represents one validation rule.
 * @param <T>
 */
public interface MetadataValidationRule<T> {

    /**
     * Validates object with this rule.
     * @param object Object to validating.
     * @return List of validation errors.
     */
    List<? extends ValidationError> validate(T object);

}
