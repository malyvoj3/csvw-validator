package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedParamLabel extends Label implements LocaleChangeObserver {

    private String propertyName;
    private Object[] params;

    public LocalizedParamLabel(String propertyName, Object... params) {
        this.propertyName = propertyName;
        this.params = params;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setText(getTranslation(propertyName, params));
    }
}
