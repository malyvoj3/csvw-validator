package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.processor.result.BatchResult;
import com.malyvoj3.csvwvalidator.processor.result.Result;

import java.io.InputStream;
import java.util.List;

public interface Processor<RESULT extends Result, BATCH_RESULT extends BatchResult<? extends Result>> {

    BATCH_RESULT process(ProcessingSettings settings, List<ProcessingInput> inputs);

    RESULT process(ProcessingSettings settings, String tabularFile, String tabularFileName,
                   String metadataFile, String metadataFileName);

    RESULT process(ProcessingSettings settings, String tabularUrl, String metadataUrl);

    RESULT processTabularData(ProcessingSettings settings, String tabularFile, String fileName);

    RESULT processMetadata(ProcessingSettings settings, String metadataFile, String fileName);

    RESULT processTabularData(ProcessingSettings settings, String tabularUrl,
                                        InputStream metadataFile, String metadataFileName);

    RESULT processMetadata(ProcessingSettings settings, String metadataUrl);


    RESULT processTabularData(ProcessingSettings settings, String tabularUrl);


}
