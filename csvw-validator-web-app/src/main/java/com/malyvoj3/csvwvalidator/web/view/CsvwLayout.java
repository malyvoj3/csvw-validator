package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.web.view.components.LocalizedAppLayoutMenuItem;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@HtmlImport("styles/validator-styles.html")
@Theme(value = Material.class, variant = Material.LIGHT)
public class CsvwLayout extends AbstractAppRouterLayout {

    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        Anchor banner = new Anchor("/", "csvw-validator");
        banner.addClassName("banner-style");
        appLayout.setBranding(banner);
        LocalizedAppLayoutMenuItem about = new LocalizedAppLayoutMenuItem("menu-item.about",
                "About", "about");
        LocalizedAppLayoutMenuItem validation = new LocalizedAppLayoutMenuItem("menu-item.validation",
                "Validation", "validation");
        appLayoutMenu.addMenuItems(validation,
                about);
    }
}
