package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.domain.ValidationError;

import java.util.List;

public interface ValidationRule<T> {

    List<? extends ValidationError> validate(T object);

}
