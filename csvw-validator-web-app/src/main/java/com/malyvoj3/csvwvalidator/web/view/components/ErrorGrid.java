package com.malyvoj3.csvwvalidator.web.view.components;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

import java.util.List;

public class ErrorGrid extends Grid<ValidationError> implements LocaleChangeObserver {

    public ErrorGrid(List<ValidationError> items) {
        super(ValidationError.class);
        setItems(items);
        setColumns("severity", "formattedMessage");
        getColumns().get(0).setResizable(true).setHeader(getTranslation("result.grid.severity"));
        getColumns().get(1).setFlexGrow(2).setHeader(getTranslation("result.grid.message"));
        setVerticalScrollingEnabled(true);
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        getColumns().get(0).setHeader(getTranslation("result.grid.severity"));
        getColumns().get(1).setHeader(getTranslation("result.grid.message"));
    }

}
