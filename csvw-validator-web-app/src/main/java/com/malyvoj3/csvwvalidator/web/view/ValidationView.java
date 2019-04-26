package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedTab;
import com.malyvoj3.csvwvalidator.web.view.pages.RemoteValidationPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
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
public class ValidationView extends MainLayout {

    public static final String VIEW_NAME = "Validation";

    public ValidationView() {
        add(createHeader("header.validation"));

        LocalizedTab tab1 = new LocalizedTab("validate-tab.remote");
        FormLayout page1 = new RemoteValidationPage();

        LocalizedTab tab2 = new LocalizedTab("validate-tab.local");
        Div page2 = new Div();
        page2.setText("Page#2");

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        Tabs tabs = new Tabs(tab1, tab2);
        Div pages = new Div(page1, page2);
        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());
        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
        tabs.setFlexGrowForEnclosedTabs(1);
        add(tabs, pages);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

}
