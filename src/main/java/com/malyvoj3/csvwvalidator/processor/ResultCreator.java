package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.validation.ValidationError;

import java.util.List;

public interface ResultCreator<T extends Result> {

    T createResult(ProcessingSettings settings,
                   List<? extends ValidationError> errors,
                   String tabularUrl,
                   String metadataUrl);

}
