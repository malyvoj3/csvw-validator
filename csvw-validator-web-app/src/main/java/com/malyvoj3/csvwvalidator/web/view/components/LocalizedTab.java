package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedTab extends Tab implements LocaleChangeObserver {

    private String propertyName;

    public LocalizedTab(String propertyName) {
        this.propertyName = propertyName;
    }

    public LocalizedTab(String propertyName, String label) {
        super(label);
        this.propertyName = propertyName;
    }

    public LocalizedTab(String propertyName, Component... components) {
        super(components);
        this.propertyName = propertyName;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setLabel(getTranslation(propertyName));
    }

}
