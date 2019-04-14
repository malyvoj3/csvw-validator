package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;

import java.util.List;

public interface ResultCreator<T extends Result, S extends BatchResult<T>> {

    T createResult(ProcessingSettings settings,
                   List<? extends ValidationError> errors,
                   String tabularUrl,
                   String metadataUrl);

    S createBatchResult(ProcessingSettings settings, List<T> results);
}
