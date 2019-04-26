package com.malyvoj3.csvwvalidator.web.view;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@HtmlImport("styles/validator-styles.html")
@Route(value = "", layout = CsvwLayout.class)
@PageTitle("cssw-validator")
public class Main2View extends MainLayout {

    public static final String VIEW_NAME = "About";

    public Main2View() {
        add(createHeader("header.main"));
    }

}
