package com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

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
