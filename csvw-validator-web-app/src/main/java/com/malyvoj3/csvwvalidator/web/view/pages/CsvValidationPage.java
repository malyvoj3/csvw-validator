package com.malyvoj3.csvwvalidator.web.view.pages;

import com.malyvoj3.csvwvalidator.web.view.ValidatingDataDTO;
import com.malyvoj3.csvwvalidator.web.view.ValidatingView;
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

public class CsvValidationPage extends VerticalLayout {

    private TextField csvTextfield;
    private Checkbox strictMode;

    public CsvValidationPage() {
        csvTextfield = createCsvTextField();
        strictMode = createStrictMode();
        add(csvTextfield);
        add(strictMode);

        Binder<ValidatingDataDTO> binder = new Binder<>(ValidatingDataDTO.class);
        binder.forField(csvTextfield)
                .asRequired(getTranslation("field.urlRequired"))
                .bind(ValidatingDataDTO::getCsvUrl, ValidatingDataDTO::setCsvUrl);
        binder.forField(strictMode)
                .bind(ValidatingDataDTO::getStrictMode, ValidatingDataDTO::setStrictMode);

        LocalizedButton validationButton = new LocalizedButton("validation.validate");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        validationButton.addClickListener(event -> {
            ValidatingDataDTO bean = new ValidatingDataDTO();
            if (binder.writeBeanIfValid(bean)) {
                // Set locale
                validationButton.getUI().ifPresent(ui -> bean.setLocale(ui.getLocale()));
                ComponentUtil.setData(UI.getCurrent(), ValidatingDataDTO.class, bean);
                validationButton.getUI().ifPresent(ui -> ui.navigate(ValidatingView.class));
            }
        });
        add(validationButton);
    }

    private Checkbox createStrictMode() {
        return new LocalizedCheckbox("validation.strictMode", true);
    }

    private TextField createCsvTextField() {
        LocalizedTextField textField = new LocalizedTextField("validation.csvUrl.label", "validation.csvUrl.placeholder");
        textField.addThemeVariants(TextFieldVariant.MATERIAL_ALWAYS_FLOAT_LABEL);
        textField.addClassName("url-textField");
        textField.setRequired(true);
        return textField;
    }

}
