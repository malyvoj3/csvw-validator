package com.malyvoj3.csvwvalidator.web.view.pages;

import com.malyvoj3.csvwvalidator.web.view.ValidatingDataDTO;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedCheckbox;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedTextField;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;

public class MetadataValidationPage extends VerticalLayout {

    private TextField metadataTextfield;
    private Checkbox strictMode;

    public MetadataValidationPage() {
        metadataTextfield = createMetadataTextField();
        strictMode = createStrictMode();
        add(metadataTextfield);
        add(strictMode);

        Binder<ValidatingDataDTO> binder = new Binder<>(ValidatingDataDTO.class);
        binder.forField(metadataTextfield)
                .asRequired(getTranslation("field.urlRequired"))
                .bind(ValidatingDataDTO::getMetadataUrl, ValidatingDataDTO::setMetadataUrl);
        binder.forField(strictMode)
                .bind(ValidatingDataDTO::getStrictMode, ValidatingDataDTO::setStrictMode);

        LocalizedButton validationButton = new LocalizedButton("validation.validate");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        validationButton.addClickListener(event -> {
            ValidatingDataDTO bean = new ValidatingDataDTO();
            if (binder.writeBeanIfValid(bean)) {
                ComponentUtil.setData(UI.getCurrent(), ValidatingDataDTO.class, bean);
                validationButton.getUI().ifPresent(ui -> ui.navigate("result"));
            }
        });
        add(validationButton);
    }

    private Checkbox createStrictMode() {
        return new LocalizedCheckbox("validation.strictMode", true);
    }

    private TextField createMetadataTextField() {
        LocalizedTextField textField = new LocalizedTextField("validation.metadataUrl.label", "validation.metadataUrl.placeholder");
        textField.addThemeVariants(TextFieldVariant.MATERIAL_ALWAYS_FLOAT_LABEL);
        textField.addClassName("url-textField");
        textField.setRequired(true);
        return textField;
    }

}
