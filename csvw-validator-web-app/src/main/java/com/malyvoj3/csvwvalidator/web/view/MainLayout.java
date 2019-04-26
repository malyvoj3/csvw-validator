package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedDiv;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainLayout extends VerticalLayout {

    protected Header createHeader(String propertyName) {
        Button cz = new Button("CZ",
                event -> getUI().get().getSession().setLocale(TranslationProvider.LOCALE_CZ));
        Button en = new Button("EN",
                event -> getUI().get().getSession().setLocale(TranslationProvider.LOCALE_EN));
        Div languages = new Div(cz, en);
        languages.addClassName("page-header-languages");
        LocalizedDiv headerText = new LocalizedDiv(propertyName);
        headerText.addClassName("page-header-text");
        Header header = new Header(headerText, languages);
        header.addClassName("page-header");
        add(header);

        return header;
    }

}
