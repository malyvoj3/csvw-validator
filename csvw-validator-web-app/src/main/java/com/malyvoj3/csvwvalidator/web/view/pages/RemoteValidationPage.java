package com.malyvoj3.csvwvalidator.web.view.pages;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedCheckbox;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedTextField;
import com.vaadin.flow.component.button.Button;
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

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(e -> {
            getUI().get().getPage().executeJavaScript("history.pushState(history.state,'','" + "result" + "');");
            removeAll();
        });
        add(validateButton);
    }

    private Checkbox createStrictMode() {
        return new LocalizedCheckbox("validate.strictMode", true);
    }

    private TextField createCsvTextField() {
        return new LocalizedTextField("validate.csvUrl");
    }

    private TextField createMetadataTextField() {
        return new LocalizedTextField("validate.metadataUrl");
    }

}
