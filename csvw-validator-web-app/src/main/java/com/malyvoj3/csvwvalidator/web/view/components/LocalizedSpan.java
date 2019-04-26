package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedSpan extends Span implements LocaleChangeObserver {

    private String propertyName;

    public LocalizedSpan(String propertyName) {
        this.propertyName = propertyName;
    }

    public LocalizedSpan(String propertyName, Component... components) {
        super(components);
        this.propertyName = propertyName;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setText(getTranslation(propertyName));
    }
}
