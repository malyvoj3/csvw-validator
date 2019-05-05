package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.Processor;
import com.malyvoj3.csvwvalidator.processor.result.*;
import com.malyvoj3.csvwvalidator.web.view.components.ErrorGrid;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedParamLabel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class ValidationThread extends Thread {

    private final UI ui;
    private final ResultView resultView;
    private final ValidatingDataDTO inputData;
    private final Processor<PersistentResult, BatchProcessingResult<PersistentResult>> processor;
    private final CsvResultWriter csvResultWriter;
    private final RdfResultWriter rdfResultWriter;

    public ValidationThread(UI ui, ResultView resultView, ValidatingDataDTO inputData,
                            Processor<PersistentResult, BatchProcessingResult<PersistentResult>> processor,
                            CsvResultWriter csvResultWriter, RdfResultWriter rdfResultWriter) {
        this.ui = ui;
        this.resultView = resultView;
        this.inputData = inputData;
        this.processor = processor;
        this.csvResultWriter = csvResultWriter;
        this.rdfResultWriter = rdfResultWriter;
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
            Component result = createResult(finalProcessingResult);
            resultView.clearProgressBar();
            resultView.add(result);
            ui.push();
        });
    }

    private Component createResult(ProcessingResult result) {
        VerticalLayout resultDiv = new VerticalLayout();
        VerticalLayout upperGrid = new VerticalLayout();

        LocalizedButton csvButton = new LocalizedButton("result.csvResults");
        FileDownloadWrapper csvButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.csv", () -> new ByteArrayInputStream(csvResultWriter.writeResult(result))));
        csvButtonWrapper.wrapComponent(csvButton);
        LocalizedButton rdfButton = new LocalizedButton("result.rdfResults");
        FileDownloadWrapper rdfButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.ttl", () -> new ByteArrayInputStream(rdfResultWriter.writeResult(result))));
        rdfButtonWrapper.wrapComponent(rdfButton);

        LocalizedParamLabel resultLabel = new LocalizedParamLabel("result.status", result.getValidationStatus().name());
        upperGrid.add(resultLabel);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                resultLabel);
        if (result.getTabularUrl() != null) {
            LocalizedParamLabel csvLabel = new LocalizedParamLabel("result.csvUrl", result.getTabularUrl());
            upperGrid.add(csvLabel);
            upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                    csvLabel);
        }
        if (result.getMetadataUrl() != null) {
            LocalizedParamLabel metadataLabel = new LocalizedParamLabel("result.metadataUrl", result.getMetadataUrl());
            upperGrid.add(metadataLabel);
            upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                    metadataLabel);
        }
        HorizontalLayout downloadButtons = new HorizontalLayout(csvButtonWrapper, rdfButtonWrapper);
        upperGrid.add(downloadButtons);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                downloadButtons);
        upperGrid.setClassName("validation-upperGrid");
        resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                upperGrid);
        resultDiv.add(upperGrid);
        List<LocalizedError> items = new ArrayList<>(result.getErrors());
        if (!items.isEmpty()) {
            ErrorGrid errorGrid = new ErrorGrid(items);
            resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH,
                    errorGrid);
            resultDiv.add(errorGrid);
        }
        resultDiv.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        upperGrid.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        LocalizedButton validationButton = new LocalizedButton("result.validationAgain");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        validationButton.addClickListener(event -> {
            validationButton.getUI().ifPresent(ui -> ui.navigate(ValidationView.class));
        });
        resultDiv.add(validationButton);
        resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                validationButton);
        return resultDiv;
    }
}
