package com.malyvoj3.csvwvalidator.metadata.parser.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.InheritanceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class DefaultPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String DEFAULT_PROPERTY_DEFAULT_VALUE = "";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty defaultValue;
        if (property.isTextual()) {
            defaultValue = new StringAtomicProperty(property, property.textValue());
        } else {
            defaultValue = new StringAtomicProperty(property, DEFAULT_PROPERTY_DEFAULT_VALUE);
        }
        description.setDefaultValue(defaultValue);
        return description;
    }
}
