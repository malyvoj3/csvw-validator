package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedCheckbox extends Checkbox implements LocaleChangeObserver {

    private String propertyName;

    public LocalizedCheckbox(String propertyName) {
        this.propertyName = propertyName;
    }

    public LocalizedCheckbox(String propertyName, String labelText) {
        super(labelText);
        this.propertyName = propertyName;
    }

    public LocalizedCheckbox(String propertyName, boolean initialValue) {
        super(initialValue);
        this.propertyName = propertyName;
    }

    public LocalizedCheckbox(String propertyName, String labelText, boolean initialValue) {
        super(labelText, initialValue);
        this.propertyName = propertyName;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setLabel(getTranslation(propertyName));
    }
}
