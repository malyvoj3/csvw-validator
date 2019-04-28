package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedParamLabel;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
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
    private final CsvwProcessor csvwProcessor;
    private final CsvResultWriter csvResultWriter;
    private final RdfResultWriter rdfResultWriter;

    public ValidationThread(UI ui, ResultView resultView, ValidatingDataDTO inputData, CsvwProcessor csvwProcessor,
                            CsvResultWriter csvResultWriter, RdfResultWriter rdfResultWriter) {
        this.ui = ui;
        this.resultView = resultView;
        this.inputData = inputData;
        this.csvwProcessor = csvwProcessor;
        this.csvResultWriter = csvResultWriter;
        this.rdfResultWriter = rdfResultWriter;
    }


    @Override
    public void run() {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(inputData.getStrictMode() == null ? true : inputData.getStrictMode());
        boolean isTabularUpload = false;/*tabularUpload != null && tabularDataFile != null;*/
        boolean isMetadataUpload = false;/*metadataUpload != null && metadataFile != null;*/
        boolean isCsvUrl = StringUtils.isNotBlank(inputData.getCsvUrl());
        boolean isMetadataUrl = StringUtils.isNotBlank(inputData.getMetadataUrl());
        VerticalLayout result = null;
        if (isTabularUpload && isMetadataUpload) {
            //showResult(csvwProcessor.processTabularData(settings, tabularDataFile, tabularDataFileName, metadataFile, metadataFileName));
        } else if (isCsvUrl && isMetadataUrl) {
            result = createResult(csvwProcessor.processTabularData(settings, inputData.getCsvUrl(), inputData.getMetadataUrl()));
        } else if (isTabularUpload && isMetadataUrl) {
            //Notification.show("Unsupported combination: uploaded tabular data file and metadata url!");
        } else if (isCsvUrl && isMetadataUpload) {
            //showResult(csvwProcessor.processTabularData(settings, tabularTextfield.getValue(), metadataFile, metadataFileName));
        } else if (isMetadataUrl) {
            result = createResult(csvwProcessor.processMetadata(settings, inputData.getMetadataUrl()));
        } else if (isCsvUrl) {
            result = createResult(csvwProcessor.processTabularData(settings, inputData.getCsvUrl()));
        } else if (isTabularUpload) {
            //showResult(csvwProcessor.processTabularData(settings, tabularDataFile, tabularDataFileName));
        } else if (isMetadataUpload) {
            //showResult(csvwProcessor.processMetadata(settings, metadataFile, metadataFileName));
        } else {
            Notification.show("Insert some files!");
        }
        VerticalLayout finalResult = result;
        ui.access(() -> {
            resultView.add(finalResult);
        });
    }

    private VerticalLayout createResult(ProcessingResult result) {
        VerticalLayout resultDiv = new VerticalLayout();
        resultDiv.setSpacing(true);

        LocalizedButton csvButton = new LocalizedButton("result.csvResults");
        FileDownloadWrapper csvButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.csv", () -> new ByteArrayInputStream(csvResultWriter.writeResult(result))));
        csvButtonWrapper.wrapComponent(csvButton);
        LocalizedButton rdfButton = new LocalizedButton("result.rdfResults");
        FileDownloadWrapper rdfButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.ttl", () -> new ByteArrayInputStream(rdfResultWriter.writeResult(result))));
        rdfButtonWrapper.wrapComponent(rdfButton);

        LocalizedParamLabel resultLabel = new LocalizedParamLabel("result.status", result.getValidationStatus().name());
        resultDiv.add(resultLabel);
        if (result.getTabularUrl() != null) {
            LocalizedParamLabel csvLabel = new LocalizedParamLabel("result.csvUrl", result.getTabularUrl());
            resultDiv.add(csvLabel);
        }
        if (result.getMetadataUrl() != null) {
            LocalizedParamLabel metadataLabel = new LocalizedParamLabel("result.metadataUrl", result.getMetadataUrl());
            resultDiv.add(metadataLabel);
        }
        resultDiv.add(new HorizontalLayout(csvButtonWrapper, rdfButtonWrapper));
        List<ValidationError> items = new ArrayList<>(result.getErrors());
        if (!items.isEmpty()) {
            Grid<ValidationError> grid = new Grid<>(ValidationError.class);
            grid.setItems(items);
            grid.setColumns("severity", "formattedMessage");
            grid.getColumns().get(0).setResizable(true).setHeader("result.grid.severity");
            grid.getColumns().get(1).setFlexGrow(2).setHeader("result.grid.message");
            grid.setHeightByRows(true);
            resultDiv.add(grid);
        }
        return resultDiv;
    }
}
