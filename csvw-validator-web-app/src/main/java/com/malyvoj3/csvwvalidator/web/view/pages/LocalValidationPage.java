package com.malyvoj3.csvwvalidator.web.view.pages;

import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.web.view.ValidatingDataDTO;
import com.malyvoj3.csvwvalidator.web.view.ValidatingView;
import com.malyvoj3.csvwvalidator.web.view.components.*;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import elemental.json.Json;

public class LocalValidationPage extends VerticalLayout {

    private static final int MAX_METADATA_FILE_SIZE = 10000000;

    private Checkbox strictMode;

    private String csvFilePath;
    private String csvFileName;

    private String metadataFilePath;
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
        validationButton.addClickListener(event -> {
            ValidatingDataDTO bean = null;
            boolean csvDefined = csvFilePath != null && csvFileName != null;
            boolean metadataDefined = metadataFilePath != null && metadataFileName != null;
            if (csvDefined && metadataDefined) {
                bean = new ValidatingDataDTO();
                bean.setCsvFilePath(csvFilePath);
                bean.setCsvFileName(csvFileName);
                bean.setMetadatFilePath(metadataFilePath);
                bean.setMetadataFileName(metadataFileName);
            } else if (csvDefined) {
                bean = new ValidatingDataDTO();
                bean.setCsvFilePath(csvFilePath);
                bean.setCsvFileName(csvFileName);
            } else if (metadataDefined) {
                bean = new ValidatingDataDTO();
                bean.setMetadatFilePath(metadataFilePath);
                bean.setMetadataFileName(metadataFileName);
            }

            if (bean != null) {
                bean.setStrictMode(strictMode.getValue());
                ValidatingDataDTO finalBean = bean;
                validationButton.getUI().ifPresent(ui -> finalBean.setLocale(ui.getLocale()));
                ComponentUtil.setData(UI.getCurrent(), ValidatingDataDTO.class, finalBean);
                validationButton.getUI().ifPresent(ui -> ui.navigate(ValidatingView.class));
            } else {
                Notification.show(getTranslation("upload.emptyInput"), 10000, Notification.Position.TOP_END);
            }
        });
        add(validationButton);
    }

    private Checkbox createStrictMode() {
        return new LocalizedCheckbox("validation.strictMode", true);
    }

    private Upload createTabularUpload() {
        FileReceiver receiver = new FileReceiver();
        LocalizedUpload upload = new LocalizedUpload(receiver);
        upload.addSucceededListener(event -> {
            csvFilePath = UriUtils.normalizeUri(receiver.getFile().toURI().toString());
            csvFileName = receiver.getFileName();
        });
        return upload;
    }

    private Upload createMetadataUpload() {
        FileReceiver receiver = new FileReceiver();
        LocalizedUpload upload = new LocalizedUpload(receiver);
        upload.addSucceededListener(event -> {
            if (receiver.getFile().length() > MAX_METADATA_FILE_SIZE) {
                Notification.show(getTranslation("upload.largeMetadata"), 10000, Notification.Position.TOP_END);
                receiver.getFile().delete();
                upload.getElement().setPropertyJson("files", Json.createArray());
            } else {
                metadataFilePath = UriUtils.normalizeUri(receiver.getFile().toURI().toString());
                metadataFileName = receiver.getFileName();
            }
        });
        return upload;
    }
}
