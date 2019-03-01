package com.malyvoj3.csvwvalidator.metadata.parser.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ColumnDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class SuppressOutputPropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final Boolean SUPPRESS_OUTPUT_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty suppressOutput;
        if (property.isBoolean()) {
            suppressOutput = new BooleanAtomicProperty(property, property.booleanValue());
        } else {
            suppressOutput = new BooleanAtomicProperty(property, SUPPRESS_OUTPUT_DEFAULT_VALUE);
        }
        description.setSuppressOutput(suppressOutput);
        return description;
    }
}
