package com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

public class OrderedPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final Boolean ORDERED_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty ordered;
        if (property.isBoolean()) {
            ordered = new BooleanAtomicProperty(property, property.booleanValue());
        } else {
            ordered = new BooleanAtomicProperty(property, ORDERED_DEFAULT_VALUE);
        }
        description.setOrdered(ordered);
        return description;
    }
}
