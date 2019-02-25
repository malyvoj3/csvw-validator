package com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.UriTemplateProperty;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

public class ValueUrlPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String VALUE_URL_DEFAULT_VALUE = "";

    @Override
    public T parseProperty(T description, JsonNode property) {
        UriTemplateProperty valueUrl;
        if (property.isTextual()) {
            // String value asi neni potreba?
            valueUrl = new UriTemplateProperty(property, property.textValue());
        } else {
            valueUrl = new UriTemplateProperty(property, VALUE_URL_DEFAULT_VALUE);
        }
        description.setValueUrl(valueUrl);
        return description;
    }

}
