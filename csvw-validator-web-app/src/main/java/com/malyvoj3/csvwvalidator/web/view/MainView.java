package com.malyvoj3.csvwvalidator.web.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.PostConstruct;

@HtmlImport("styles/validator-styles.html")
@Route(value = "", layout = CsvwLayout.class)
@PageTitle("cssw-validator")
public class MainView extends MainLayout {

    public MainView() {
        getUI().ifPresent(ui -> ui.navigate("validation"));
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        attachEvent.getUI().navigate("validation");
    }

    @PostConstruct
    public void init() {
        getUI().ifPresent(ui -> ui.navigate("validation"));
    }

    /*@Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        removeAll();
        buildView();
    }

    private void buildView() {
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
    }*/

}
