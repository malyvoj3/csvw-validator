package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;

import java.util.List;

public interface MetadataValidationRule<T> {

    List<? extends ValidationError> validate(T object);

}
