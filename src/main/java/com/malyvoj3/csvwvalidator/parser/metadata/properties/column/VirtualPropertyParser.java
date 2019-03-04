package com.malyvoj3.csvwvalidator.parser.metadata.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class VirtualPropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final Boolean VIRTUAL_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty virtual;
        if (property.isBoolean()) {
            virtual = new BooleanAtomicProperty(property.booleanValue());
        } else {
            virtual = new BooleanAtomicProperty(VIRTUAL_DEFAULT_VALUE);
        }
        description.setSuppressOutput(virtual);
        return description;
    }
}
