package com.malyvoj3.csvwvalidator.web.view.pages;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedCheckbox;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedLabel;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedUpload;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.InputStream;

public class LocalValidationPage extends VerticalLayout {

    private Checkbox strictMode;

    private InputStream csvDataFile;
    private String csvDataFileName;

    private InputStream metadataFile;
    private String metadataFileName;

    public LocalValidationPage() {
        add(new LocalizedLabel("upload.csv"));
        add(createTabularUpload());
        add(new LocalizedLabel("upload.metadata"));
        add(createMetadataUpload());
        strictMode = createStrictMode();
        add(strictMode);
        LocalizedButton validationButton = new LocalizedButton("validation.validate");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        add(validationButton);
    }

    private Checkbox createStrictMode() {
        return new LocalizedCheckbox("validation.strictMode", true);
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
