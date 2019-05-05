package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.processor.result.BatchResult;
import com.malyvoj3.csvwvalidator.processor.result.Result;

import java.util.List;

public interface Processor<RESULT extends Result, BATCH_RESULT extends BatchResult<RESULT>> {

    BATCH_RESULT process(ProcessingContext context, List<ProcessingInput> inputs);

    RESULT process(ProcessingContext context, String tabularFile, String tabularFileName,
                   String metadataFile, String metadataFileName);

    RESULT process(ProcessingContext context, String tabularUrl, String metadataUrl);

    RESULT processTabularData(ProcessingContext context, String tabularFile, String fileName);

    RESULT processMetadata(ProcessingContext context, String metadataFile, String fileName);

    RESULT processMetadata(ProcessingContext context, String metadataUrl);


    RESULT processTabularData(ProcessingContext context, String tabularUrl);


}
