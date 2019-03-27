package com.malyvoj3.csvwvalidator.validation;

import java.util.List;

public interface ValidationRule<T> {

    List<? extends ValidationError> validate(T object);

}
