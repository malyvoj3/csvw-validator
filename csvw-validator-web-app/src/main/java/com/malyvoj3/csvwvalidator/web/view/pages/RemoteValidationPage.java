package com.malyvoj3.csvwvalidator.web.view.pages;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class RemoteValidationPage extends FormLayout {

    private TextField metadataTextfield;
    private TextField csvTextfield;
    private Checkbox strictMode;

    public RemoteValidationPage() {
        metadataTextfield = createMetadataTextField();
        csvTextfield = createCsvTextField();
        strictMode = createStrictMode();
        add(metadataTextfield);
        add(csvTextfield);
        add(strictMode);
        addFormItem(new TextField(), "Test input");
    }

    private Checkbox createStrictMode() {
        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Strict mode");
        return checkbox;
    }

    private TextField createCsvTextField() {
        TextField textField = new TextField();
        textField.setLabel("CSV file URL");
        textField.addThemeVariants();
        return textField;
    }

    private TextField createMetadataTextField() {
        TextField textField = new TextField();
        textField.setLabel("CSV on the Web descriptor URL");
        return textField;
    }

}
