package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class SkipInitialSpacePropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean SKIP_INITIAL_SPACE_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty skipInitialSpace;
        if (property.isBoolean()) {
            skipInitialSpace = new BooleanAtomicProperty(property, property.booleanValue());
        } else {
            skipInitialSpace = new BooleanAtomicProperty(property, SKIP_INITIAL_SPACE_DEFAULT_VALUE);
        }
        description.setSkipInitialSpace(skipInitialSpace);
        return description;
    }
}
