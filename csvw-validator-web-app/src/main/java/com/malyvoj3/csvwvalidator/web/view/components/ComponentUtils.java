package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.Component;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ComponentUtils {

    public LocalizedAppLayoutMenuItem createMenuItem(String propertyName, String title, String route) {
        return new LocalizedAppLayoutMenuItem(propertyName, title, route);
    }

    public LocalizedDiv createDiv(String propertyName) {
        return new LocalizedDiv(propertyName);
    }

    public LocalizedDiv createDiv(String propertyName, Component... components) {
        return new LocalizedDiv(propertyName, components);
    }

}
