package com.malyvoj3.csvwvalidator.parser.metadata.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class OrderedPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final Boolean ORDERED_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty ordered;
        if (property.isBoolean()) {
            ordered = new BooleanAtomicProperty(property.booleanValue());
        } else {
            ordered = new BooleanAtomicProperty(ORDERED_DEFAULT_VALUE);
        }
        description.setOrdered(ordered);
        return description;
    }
}
