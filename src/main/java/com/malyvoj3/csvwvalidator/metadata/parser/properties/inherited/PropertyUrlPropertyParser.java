package com.malyvoj3.csvwvalidator.metadata.parser.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.InheritanceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.UriTemplateProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class PropertyUrlPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String PROPERTY_URL_DEFAULT_VALUE = "";

    @Override
    public T parseProperty(T description, JsonNode property) {
        UriTemplateProperty propertyUrl;
        if (property.isTextual()) {
            // String value asi neni potreba?
            propertyUrl = new UriTemplateProperty(property, property.textValue());
        } else {
            propertyUrl = new UriTemplateProperty(property, PROPERTY_URL_DEFAULT_VALUE);
        }
        description.setPropertyUrl(propertyUrl);
        return description;
    }
}
