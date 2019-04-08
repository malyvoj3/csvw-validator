package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.validation.ValidationError;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.ByteArrayOutputStream;

public class CsvResultWriter implements ResultWriter {

    @Override
    public byte[] writeResult(ProcessingResult processingResult) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CsvWriter writer = new CsvWriter(outputStream, "UTF-8", createSettings());
        try {
            writer.writeHeaders("Error severity", "Error message");
            for (ValidationError error : processingResult.getErrors()) {
                writer.writeRow(error.getSeverity(), error.getFormattedMessage());
            }
        } finally {
            writer.close();
        }
        return outputStream.toByteArray();
    }

    private CsvWriterSettings createSettings() {
        CsvFormat format = new CsvFormat();
        format.setQuoteEscape('"');
        format.setQuote('"');
        format.setDelimiter(',');
        format.setLineSeparator("\r\n");
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setFormat(format);
        settings.setNullValue("");
        settings.setNormalizeLineEndingsWithinQuotes(false);
        return settings;
    }

}
