package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import com.malyvoj3.csvwvalidator.validation.JsonParserErrorDefaultFormatter;
import com.malyvoj3.csvwvalidator.validation.ValidationErrorFormatter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private static final String URL_MODE = "URL";
    private static final String UPLOAD_MODE = "File upload";

    @Autowired
    private MetadataParser metadataParser;

    private InputStream tabularDataFile;
    private List<InputStream> metadataFiles;

    private Upload tabularUpload;
    private TextField tabularTextfield;

    private Upload metadataUpload;
    private TextField metadataTextfield;

    public MainView() {
        showUpload();
    }

    private void showUpload() {
        removeAll();
        tabularDataFile = null;
        metadataFiles = new ArrayList<>();

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

    private void showResult(InputStream inputStream, String url) {
        removeAll();

        ValidationErrorFormatter<JsonParserError> formatter = new JsonParserErrorDefaultFormatter();
        MetadataParsingResult result = metadataParser.parseJson(inputStream, url);
        result.getParsingErrors().forEach(error -> {
            Label label = new Label(formatter.format(error));
            add(label);
        });

        add(createBackButton());
    }

    private Button createValidationButton() {
        Button validationButton = new Button("Validate");
        validationButton.addClickListener(e -> {
            if (metadataUpload != null && metadataFiles.size() > 0) {
                showResult(metadataFiles.get(0), "http://example.com");
            } else if (metadataTextfield != null && metadataTextfield.getValue() != null) {
                showResult(new ByteArrayInputStream(FileUtils.downloadFile(metadataTextfield.getValue()).getContent()), metadataTextfield.getValue());
            } else {
                Notification.show("Insert some files!");
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
        upload.setAcceptedFileTypes("text/csv");
        return upload;
    }

    private Upload createMetadataUpload() {
        MultiFileMemoryBuffer multiLineBuffer = new MultiFileMemoryBuffer();
        Upload metadataUpload = new Upload(multiLineBuffer);
        metadataUpload.setAcceptedFileTypes("application/json");
        metadataUpload.addSucceededListener(event -> {
            metadataFiles.add(multiLineBuffer.getInputStream(event.getFileName()));
        });
        return metadataUpload;
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