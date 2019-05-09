package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.processor.ProcessingContext;

import java.util.List;

/**
 * Creates results after validation finish.
 * @param <T> Type of the result.
 * @param <S> Type of the batch result.
 */
public interface ResultCreator<T extends Result, S extends BatchResult<T>> {

    /**
     * Create result for one validation.
     * @param context Validation context.
     * @param tabularIrl Iri of the tabulara file.
     * @param metadataIrl Iri of the metadata file.
     * @return Created result.
     */
    T createResult(ProcessingContext context,
                   String tabularIrl,
                   String metadataIrl);

    /**
     * Create batch result for multiple validations.
     * @param context Validation context.
     * @param results Results which batch result contains.
     * @return Created batch result.
     */
    S createBatchResult(ProcessingContext context, List<T> results);
}
