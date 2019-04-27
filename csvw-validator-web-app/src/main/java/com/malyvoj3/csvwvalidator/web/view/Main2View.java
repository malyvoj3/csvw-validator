package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@HtmlImport("styles/validator-styles.html")
@Route(value = "", layout = CsvwLayout.class)
@PageTitle("cssw-validator")
public class Main2View extends MainLayout implements LocaleChangeObserver {

    public Main2View() {
        buildView();
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        removeAll();
        buildView();
    }

    private void buildView(){
        add(createHeader("header.main"));
        add(new Html(getTranslation("pageText.main")));
        LocalizedButton validationButton = new LocalizedButton("main.startValidation");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        validationButton.addClickListener(event -> {
            validationButton.getUI().ifPresent(ui -> ui.navigate("validation"));
        });
        add(validationButton);
        setSizeFull();
    }

}
