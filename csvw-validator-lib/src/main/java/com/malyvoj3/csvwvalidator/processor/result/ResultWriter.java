package com.malyvoj3.csvwvalidator.processor.result;

/**
 * Serialize {@link ProcessingResult} to file (byte array).
 * @param <T>
 */
public interface ResultWriter<T extends ProcessingResult> {

    /**
     * Create result file.
     * @param processingResult Result to serialize.
     * @return Byte of arrays which forms the result file.
     */
    byte[] writeResult(T processingResult);

}
