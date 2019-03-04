package com.malyvoj3.csvwvalidator.parser.metadata.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.UriTemplateProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class ValueUrlPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String VALUE_URL_DEFAULT_VALUE = "";

    @Override
    public T parseProperty(T description, JsonNode property) {
        UriTemplateProperty valueUrl;
        if (property.isTextual()) {
            // String value asi neni potreba?
            valueUrl = new UriTemplateProperty(property.textValue());
        } else {
            valueUrl = new UriTemplateProperty(VALUE_URL_DEFAULT_VALUE);
        }
        description.setValueUrl(valueUrl);
        return description;
    }

}
