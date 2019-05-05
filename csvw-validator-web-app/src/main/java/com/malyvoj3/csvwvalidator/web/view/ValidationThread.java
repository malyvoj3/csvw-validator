package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.Processor;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.PersistentResult;
import com.vaadin.flow.component.UI;
import org.apache.commons.lang3.StringUtils;

public class ValidationThread extends Thread {

    private final UI ui;
    private final ValidatingDataDTO inputData;
    private final Processor<PersistentResult, BatchProcessingResult<PersistentResult>> processor;

    public ValidationThread(UI ui, ValidatingView resultView, ValidatingDataDTO inputData,
                            Processor<PersistentResult, BatchProcessingResult<PersistentResult>> processor) {
        this.ui = ui;
        this.inputData = inputData;
        this.processor = processor;
    }

    @Override
    public void run() {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(inputData.getStrictMode() == null ? true : inputData.getStrictMode());
        if (inputData.getLocale() != null) {
            settings.setLocale(inputData.getLocale());
        }
        boolean isTabularUpload = StringUtils.isNotBlank(inputData.getCsvFilePath())
                && StringUtils.isNotBlank(inputData.getCsvFileName());
        boolean isMetadataUpload = StringUtils.isNotBlank(inputData.getMetadatFilePath())
                && StringUtils.isNotBlank(inputData.getMetadataFileName());
        boolean isCsvUrl = StringUtils.isNotBlank(inputData.getCsvUrl());
        boolean isMetadataUrl = StringUtils.isNotBlank(inputData.getMetadataUrl());
        PersistentResult persistentResult = null;
        if (isTabularUpload && isMetadataUpload) {
            persistentResult = processor.process(settings, inputData.getCsvFilePath(), inputData.getCsvFileName(),
                    inputData.getMetadatFilePath(), inputData.getMetadataFileName());
        } else if (isCsvUrl && isMetadataUrl) {
            persistentResult = processor.process(settings, inputData.getCsvUrl(), inputData.getMetadataUrl());
        } else if (isMetadataUrl) {
            persistentResult = processor.processMetadata(settings, inputData.getMetadataUrl());
        } else if (isCsvUrl) {
            persistentResult = processor.processTabularData(settings, inputData.getCsvUrl());
        } else if (isTabularUpload) {
            persistentResult = processor.processTabularData(settings, inputData.getCsvFilePath(),
                    inputData.getCsvFileName());
        } else if (isMetadataUpload) {
            persistentResult = processor.processMetadata(settings, inputData.getMetadatFilePath(),
                    inputData.getMetadataFileName());
        } else {
            throw new IllegalStateException("Invalid combination of inputs for processing.");
        }
        PersistentResult finalProcessingResult = persistentResult;
        ui.access(() -> {
            ui.navigate(ResultView.class, finalProcessingResult.getId());
            ui.push();
        });
    }

}
