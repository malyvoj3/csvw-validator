package com.malyvoj3.csvwvalidator.processor.result;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

@Slf4j
public class TextResultWriter implements ResultWriter {

    @Override
    public byte[] writeResult(ProcessingResult processingResult) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(createOutput(processingResult).getBytes());
        } catch (IOException e) {
            log.error("Exception during writing result.");
        }
        return outputStream.toByteArray();
    }

    private String createOutput(ProcessingResult result) throws IOException {
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        if (result.getTabularUrl() != null) {
            writer.write("Tabular URL: " + result.getTabularUrl());
            writer.newLine();
        }
        if (result.getMetadataUrl() != null) {
            writer.write("Metadata URL: " + result.getMetadataUrl());
            writer.newLine();
        }
        writer.write("Result: " + result.getValidationStatus().name());
        writer.newLine();
        writer.write("Strict mode: " + (result.getSettings().isStrictMode() ? "true" : "false"));
        writer.newLine();
        writer.write("Total errors: " + result.getTotalErrorsCount());
        writer.newLine();
        writer.write("Warning errors: " + result.getWarningCount());
        writer.newLine();
        writer.write("Error errors: " + result.getErrorCount());
        writer.newLine();
        writer.write("Fatal errors: " + result.getFatalCount());
        writer.newLine();
        writer.write("Errors:");
        writer.newLine();
        for (LocalizedError error : result.getErrors()) {
            writer.write("  " + error.getSeverity()+ ": " + error.getMessage());
            writer.newLine();
        }
        writer.flush();
        writer.close();
        return stringWriter.getBuffer().toString();
    }

}
