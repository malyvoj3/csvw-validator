package com.malyvoj3.csvwvalidator.metadata.parser.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.InheritanceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class SeparatorPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String SEPARATOR_DEFAULT_VALUE = null;

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty separator;
        if (property.isTextual()) {
            separator = new StringAtomicProperty(property, property.textValue());
        } else {
            separator = new StringAtomicProperty(property, SEPARATOR_DEFAULT_VALUE);
        }
        description.setSeparator(separator);
        return description;
    }
}
