package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private static final String URL_MODE = "URL";
    private static final String UPLOAD_MODE = "File upload";

    @Autowired
    private CsvwProcessor csvwProcessor;

    @Autowired
    private CsvResultWriter csvResultWriter;

    @Autowired
    private RdfResultWriter rdfResultWriter;

    private InputStream tabularDataFile;
    private String tabularDataFileName;
    private InputStream metadataFile;
    private String metadataFileName;

    private Upload tabularUpload;
    private TextField tabularTextfield;

    private Upload metadataUpload;
    private TextField metadataTextfield;

    public MainView() {
        showUpload();
    }

    private void showUpload() {
        clean();
        add(createLabel("Insert tabular data via:"));
        add(createTabularMode());
        tabularUpload = createTabularUpload();
        add(tabularUpload);
        add(createLabel("Insert metadata files via:"));
        add(createMetadataMode());
        metadataUpload = createMetadataUpload();
        add(metadataUpload);
        add(createValidationButton());
    }

    private void clean() {
        removeAll();
        tabularDataFile = null;
        tabularDataFileName = null;
        metadataFile = null;
        metadataFileName = null;
        tabularTextfield = null;
        metadataTextfield = null;
        tabularUpload = null;
        metadataUpload = null;
    }

    private void showResult(ProcessingResult result) {
        removeAll();

        Button csvButton = new Button("Download CSV");
        FileDownloadWrapper csvButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.csv", () -> new ByteArrayInputStream(csvResultWriter.writeResult(result))));
        csvButtonWrapper.wrapComponent(csvButton);
        Button rdfButton = new Button("Download RDF");
        FileDownloadWrapper rdfButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.ttl", () -> new ByteArrayInputStream(rdfResultWriter.writeResult(result))));
        rdfButtonWrapper.wrapComponent(rdfButton);

        Grid<ValidationError> grid = new Grid<>(ValidationError.class);
        List<ValidationError> items = new ArrayList<>(result.getErrors());
        grid.setItems(items);
        grid.setColumns("severity", "formattedMessage");
        add(new Label(String.format("Validation result: %s", result.getValidationStatus().name())));
        if (result.getTabularUrl() != null) {
            add(new Label(String.format("Tabular file: %s", result.getTabularUrl())));
        }
        if (result.getMetadataUrl() != null) {
            add(new Label(String.format("Metadata file: %s", result.getMetadataUrl())));
        }
        add(csvButtonWrapper);
        add(rdfButtonWrapper);
        add(grid);
        add(createBackButton());
    }

    private Button createValidationButton() {
        Button validationButton = new Button("Validate");
        ProcessingSettings settings = new ProcessingSettings(); // default strict mode
        validationButton.addClickListener(e -> {
            try {
                boolean isTabularUpload = tabularUpload != null && tabularDataFile != null;
                boolean isMetadataUpload = metadataUpload != null && metadataFile != null;
                boolean isTabularUrl = tabularTextfield != null && StringUtils.isNotBlank(tabularTextfield.getValue());
                boolean isMetadataUrl = metadataTextfield != null && StringUtils.isNotBlank(metadataTextfield.getValue());
                if (isTabularUpload && isMetadataUpload) {
                    showResult(csvwProcessor.processTabularData(settings, tabularDataFile, tabularDataFileName, metadataFile, metadataFileName));
                } else if (isTabularUrl && isMetadataUrl) {
                    showResult(csvwProcessor.processTabularData(settings, tabularTextfield.getValue(), metadataTextfield.getValue()));
                } else if (isTabularUpload && isMetadataUrl) {
                    Notification.show("Unsupported combination: uploaded tabular data file and metadata url!");
                } else if (isTabularUrl && isMetadataUpload) {
                    showResult(csvwProcessor.processTabularData(settings, tabularTextfield.getValue(), metadataFile, metadataFileName));
                } else if (isMetadataUrl) {
                    showResult(csvwProcessor.processMetadata(settings, metadataTextfield.getValue()));
                } else if (isTabularUrl) {
                    showResult(csvwProcessor.processTabularData(settings, tabularTextfield.getValue()));
                } else if (isTabularUpload) {
                    showResult(csvwProcessor.processTabularData(settings, tabularDataFile, tabularDataFileName));
                } else if (isMetadataUpload) {
                    showResult(csvwProcessor.processMetadata(settings, metadataFile, metadataFileName));
                } else {
                    Notification.show("Insert some files!");
                }
            } catch (Exception ex) {
                Notification.show("Exception: " + ex.getMessage(), 5000, Notification.Position.TOP_END);
                throw ex;
            }

        });
        return validationButton;
    }

    private Button createBackButton() {
        Button backButton = new Button("Back");
        backButton.addClickListener(e -> {
            showUpload();
        });
        return backButton;
    }

    private Label createLabel(String label) {
        return new Label(label);
    }

    private RadioButtonGroup<String> createTabularMode() {
        RadioButtonGroup<String> tabularGroup = new RadioButtonGroup<>();
        tabularGroup.setItems(UPLOAD_MODE, URL_MODE);
        tabularGroup.setValue(UPLOAD_MODE);
        tabularGroup.addValueChangeListener(event -> {
            if (!event.getOldValue().equals(event.getValue())) {
                if (URL_MODE.equals(event.getValue())) {
                    tabularTextfield = createTabularTextField();
                    replace(tabularUpload, tabularTextfield);
                    tabularUpload = null;
                } else {
                    tabularUpload = createTabularUpload();
                    replace(tabularTextfield, tabularUpload);
                    tabularTextfield = null;
                }
            }
        });
        return tabularGroup;
    }

    private RadioButtonGroup<String> createMetadataMode() {
        RadioButtonGroup<String> metadataGroup = new RadioButtonGroup<>();
        metadataGroup.setItems(UPLOAD_MODE, URL_MODE);
        metadataGroup.setValue(UPLOAD_MODE);
        metadataGroup.addValueChangeListener(event -> {
            if (!event.getOldValue().equals(event.getValue())) {
                if (URL_MODE.equals(event.getValue())) {
                    metadataTextfield = createMetadataTextField();
                    replace(metadataUpload, metadataTextfield);
                    metadataUpload = null;
                } else {
                    metadataUpload = createMetadataUpload();
                    replace(metadataTextfield, metadataUpload);
                    metadataTextfield = null;
                }
            }
        });
        return metadataGroup;
    }

    private Upload createTabularUpload() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.addSucceededListener(event -> {
            tabularDataFile = buffer.getInputStream();
            tabularDataFileName = buffer.getFileName();
        });
        return upload;
    }

    private Upload createMetadataUpload() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/json");
        upload.addSucceededListener(event -> {
            metadataFile = buffer.getInputStream();
            metadataFileName = buffer.getFileName();
        });
        return upload;
    }

    private TextField createTabularTextField() {
        TextField textField = new TextField();
        textField.setLabel("Tabular data file URL");
        return textField;
    }

    private TextField createMetadataTextField() {
        TextField textField = new TextField();
        textField.setLabel("Metadata file URL");
        return textField;
    }

}