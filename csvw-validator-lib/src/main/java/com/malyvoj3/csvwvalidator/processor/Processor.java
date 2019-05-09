package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.processor.result.BatchResult;
import com.malyvoj3.csvwvalidator.processor.result.Result;

import java.util.List;

/**
 * Main logic of validator, which performs all steps from which validation composed.
 * @param <RESULT> Type of validation result.
 * @param <BATCH_RESULT> Type of batch validation result.
 */
public interface Processor<RESULT extends Result, BATCH_RESULT extends BatchResult<RESULT>> {

    /**
     * Validates multiple files all at once.
     * @param context Processing context.
     * @param inputs List of tabular files and/or metadat files to process.
     * @return Result of batch validation.
     */
    BATCH_RESULT process(ProcessingContext context, List<ProcessingInput> inputs);

    /**
     * Validates local tabular file with local metadata file.
     * @param context Processing context.
     * @param tabularFile IRI of the local tabular file.
     * @param tabularFileName Name of the tabular file.
     * @param metadataFile IRI of the local metadata file.
     * @param metadataFileName Name of the metadata file.
     * @return Result of the validation.
     */
    RESULT process(ProcessingContext context, String tabularFile, String tabularFileName,
                   String metadataFile, String metadataFileName);

    /**
     * Validates remote tabular file with remote metadata file.
     * @param context Processing context.
     * @param tabularUrl IRI of the remote tabular file.
     * @param metadataUrl IRI of the remote metadata file.
     * @return Result of the validation.
     */
    RESULT process(ProcessingContext context, String tabularUrl, String metadataUrl);

    /**
     * Validates local tabular file. Just tabular format is validated.
     * @param context Processing context.
     * @param tabularFile IRI of the local tabular file.
     * @param fileName Name of the tabular file.
     * @return Result of the validation.
     */
    RESULT processTabularData(ProcessingContext context, String tabularFile, String fileName);

    /**
     * Validates local metadata file. Just metadata format is validated.
     * @param context Processing context.
     * @param metadataFile IRI of the local metadata file.
     * @param fileName Name of the metadata file.
     * @return Result of the validation.
     */
    RESULT processMetadata(ProcessingContext context, String metadataFile, String fileName);

    /**
     * Validates remote metadata file.
     * @param context Processing context.
     * @param metadataUrl IRI of the remote metadata file.
     * @return Result of the validation.
     */
    RESULT processMetadata(ProcessingContext context, String metadataUrl);


    /**
     * Validates remote tabular file. if metadata file is not localized, then just tabular file format is validated.
     * @param context Processing context.
     * @param tabularUrl IRI of the remote tabular file.
     * @return Result of the validation.
     */
    RESULT processTabularData(ProcessingContext context, String tabularUrl);


}
