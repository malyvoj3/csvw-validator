package com.malyvoj3.csvwvalidator.web.view.pages;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedUpload;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.InputStream;

public class LocalValidationPage extends VerticalLayout {

    private InputStream csvDataFile;
    private String csvDataFileName;

    private InputStream metadataFile;
    private String metadataFileName;

    public LocalValidationPage() {
        add(new Label("Upload CSV file from your computer"));
        add(createTabularUpload());
        add(new Label("Upload metadata from your computer:"));
        add(createMetadataUpload());
    }

    private Upload createTabularUpload() {
        MemoryBuffer buffer = new MemoryBuffer();
        LocalizedUpload upload = new LocalizedUpload(buffer);
        upload.addSucceededListener(event -> {
            csvDataFile = buffer.getInputStream();
            csvDataFileName = buffer.getFileName();
        });
        return upload;
    }

    private Upload createMetadataUpload() {
        MemoryBuffer buffer = new MemoryBuffer();
        LocalizedUpload upload = new LocalizedUpload(buffer);
        upload.setAcceptedFileTypes("application/json");
        upload.addSucceededListener(event -> {
            metadataFile = buffer.getInputStream();
            metadataFileName = buffer.getFileName();
        });
        return upload;
    }

}
