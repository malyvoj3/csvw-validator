package com.malyvoj3.csvwvalidator.parser.metadata.properties.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class SuppressOutputPropertyParser<T extends TableDescription> implements PropertyParser<T> {

    private static final Boolean SUPPRESS_OUTPUT_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty suppressOutput;
        if (property.isBoolean()) {
            suppressOutput = new BooleanAtomicProperty(property.booleanValue());
        } else {
            suppressOutput = new BooleanAtomicProperty(SUPPRESS_OUTPUT_DEFAULT_VALUE);
        }
        description.setSuppressOutput(suppressOutput);
        return description;
    }
}
