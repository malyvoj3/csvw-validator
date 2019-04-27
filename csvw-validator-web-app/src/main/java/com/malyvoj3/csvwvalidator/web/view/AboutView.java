package com.malyvoj3.csvwvalidator.web.view;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@HtmlImport("styles/validator-styles.html")
@Route(value = "about", layout = CsvwLayout.class)
@PageTitle("About cssw-validator")
public class AboutView extends MainLayout implements LocaleChangeObserver {

    public AboutView() {
        buildView();
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        removeAll();
        buildView();
    }

    private void buildView(){
        add(createHeader("header.about"));
        add(new Html(getTranslation("pageText.about")));
        setSizeFull();
    }
}
