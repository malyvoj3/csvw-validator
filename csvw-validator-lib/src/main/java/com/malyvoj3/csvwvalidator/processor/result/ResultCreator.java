package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.processor.ProcessingContext;

import java.util.List;

public interface ResultCreator<T extends Result, S extends BatchResult<T>> {

    T createResult(ProcessingContext context,
                   String tabularUrl,
                   String metadataUrl);

    S createBatchResult(ProcessingContext context, List<T> results);
}
