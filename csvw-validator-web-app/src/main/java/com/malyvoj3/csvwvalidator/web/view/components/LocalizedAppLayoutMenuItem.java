package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedAppLayoutMenuItem extends AppLayoutMenuItem implements LocaleChangeObserver {

    private String propertyName;

    public LocalizedAppLayoutMenuItem(String propertyName, String title) {
        super(title);
        this.propertyName = propertyName;
    }

    public LocalizedAppLayoutMenuItem(String propertyName, Component icon) {
        super(icon);
        this.propertyName = propertyName;
    }

    public LocalizedAppLayoutMenuItem(String propertyName, Component icon, String title) {
        super(icon, title);
        this.propertyName = propertyName;
    }

    public LocalizedAppLayoutMenuItem(String propertyName, String title, String route) {
        super(title, route);
        this.propertyName = propertyName;
    }

    public LocalizedAppLayoutMenuItem(String propertyName, Component icon, String title, String route) {
        super(icon, title, route);
        this.propertyName = propertyName;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setTitle(getTranslation(propertyName));
    }

}
