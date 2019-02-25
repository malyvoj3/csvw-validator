package com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

public class RequiredPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final Boolean REQUIRED_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty required;
        if (property.isBoolean()) {
            required = new BooleanAtomicProperty(property, property.booleanValue());
        } else {
            required = new BooleanAtomicProperty(property, REQUIRED_DEFAULT_VALUE);
        }
        description.setOrdered(required);
        return description;
    }
}
