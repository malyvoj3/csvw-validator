package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedTab;
import com.malyvoj3.csvwvalidator.web.view.pages.CsvValidationPage;
import com.malyvoj3.csvwvalidator.web.view.pages.LocalValidationPage;
import com.malyvoj3.csvwvalidator.web.view.pages.MetadataValidationPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@HtmlImport("styles/validator-styles.html")
@Route(value = "validation", layout = CsvwLayout.class)
@PageTitle("Validation")
public class ValidationView extends MainLayout implements LocaleChangeObserver {

    public static final String VIEW_NAME = "Validation";

    public ValidationView() {
        buildView();
    }

    private void buildView() {
        add(createHeader("header.validation"));
        add(new Html(getTranslation("pageText.validation")));
        buildTabs();

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void buildTabs() {
        LocalizedTab tab1 = new LocalizedTab("validation.tab.metadata");
        MetadataValidationPage page1 = new MetadataValidationPage();
        page1.addClassName("tab-page");

        LocalizedTab tab2 = new LocalizedTab("validation.tab.csv");
        CsvValidationPage page2 = new CsvValidationPage();
        page2.addClassName("tab-page");

        LocalizedTab tab3 = new LocalizedTab("validation.tab.local");
        LocalValidationPage page3 = new LocalValidationPage();
        page3.addClassName("tab-page");

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        Tabs tabs = new Tabs(tab1, tab2, tab3);
        Div pages = new Div(page1, page2, page3);
        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());
        page2.setVisible(false);
        page3.setVisible(false);
        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
        tabs.setFlexGrowForEnclosedTabs(1);
        add(tabs, pages);
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        removeAll();
        buildView();
    }
}
