package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedButton extends Button implements LocaleChangeObserver {

    private String propertyName;

    public LocalizedButton(String propertyName) {
        this.propertyName = propertyName;
    }

    public LocalizedButton(String text, String propertyName) {
        super(text);
        this.propertyName = propertyName;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setText(getTranslation(propertyName));
    }
}
