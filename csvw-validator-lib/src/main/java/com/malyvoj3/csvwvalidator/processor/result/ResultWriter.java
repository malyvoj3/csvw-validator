package com.malyvoj3.csvwvalidator.processor.result;

public interface ResultWriter<T extends ProcessingResult> {

    byte[] writeResult(T processingResult);

}
