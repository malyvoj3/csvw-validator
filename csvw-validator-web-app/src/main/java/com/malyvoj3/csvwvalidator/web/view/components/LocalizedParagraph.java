package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedParagraph extends Paragraph implements LocaleChangeObserver {

    private String propertyName;

    public LocalizedParagraph(String propertyName) {
        this.propertyName = propertyName;
    }

    public LocalizedParagraph(String propertyName, Component... components) {
        super(components);
        this.propertyName = propertyName;
    }

    public LocalizedParagraph(String text, String propertyName) {
        super(text);
        this.propertyName = propertyName;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setText(getTranslation(propertyName));
    }
}
