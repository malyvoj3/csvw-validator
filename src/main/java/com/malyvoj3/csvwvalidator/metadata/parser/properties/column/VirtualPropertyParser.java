package com.malyvoj3.csvwvalidator.metadata.parser.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ColumnDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class VirtualPropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final Boolean VIRTUAL_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty virtual;
        if (property.isBoolean()) {
            virtual = new BooleanAtomicProperty(property, property.booleanValue());
        } else {
            virtual = new BooleanAtomicProperty(property, VIRTUAL_DEFAULT_VALUE);
        }
        description.setSuppressOutput(virtual);
        return description;
    }
}
