package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class SkipInitialSpacePropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean SKIP_INITIAL_SPACE_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty skipInitialSpace;
        if (property.isBoolean()) {
            skipInitialSpace = new BooleanAtomicProperty(property.booleanValue());
        } else {
            skipInitialSpace = new BooleanAtomicProperty(SKIP_INITIAL_SPACE_DEFAULT_VALUE);
        }
        description.setSkipInitialSpace(skipInitialSpace);
        return description;
    }
}
