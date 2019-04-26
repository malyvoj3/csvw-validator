package com.malyvoj3.csvwvalidator.web.view;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@HtmlImport("styles/validator-styles.html")
@Route(value = "about", layout = CsvwLayout.class)
@PageTitle("About cssw-validator")
public class AboutView extends MainLayout {

    public static final String VIEW_NAME = "About";

    public AboutView() {
        add(createHeader("header.about"));
        setSizeFull();
    }

}
