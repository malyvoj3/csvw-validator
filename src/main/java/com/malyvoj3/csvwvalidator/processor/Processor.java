package com.malyvoj3.csvwvalidator.processor;

import java.io.InputStream;
import java.util.List;

public interface Processor<RESULT extends Result, BATCH_RESULT extends BatchResult<? extends Result>> {

    BATCH_RESULT processTabularData(ProcessingSettings settings, List<ProcessingInput> inputs);

    RESULT processTabularData(ProcessingSettings settings, InputStream file, String fileName);

    RESULT processMetadata(ProcessingSettings settings, InputStream file, String fileName);

    RESULT processTabularData(ProcessingSettings settings, InputStream tabularFile,
                                        String tabularFileName, InputStream metadataFile, String metadataFileName);

    RESULT processTabularData(ProcessingSettings settings, String tabularUrl,
                                        InputStream metadataFile, String metadataFileName);

    RESULT processMetadata(ProcessingSettings settings, String metadataUrl);

    RESULT processTabularData(ProcessingSettings settings, String tabularUrl, String metadataUrl);

    RESULT processTabularData(ProcessingSettings settings, String tabularUrl);


}
