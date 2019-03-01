package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class SkipBlankRowsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean SKIP_BLANK_ROWS_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty skipBlankRows;
        if (property.isBoolean()) {
            skipBlankRows = new BooleanAtomicProperty(property, property.booleanValue());
        } else {
            skipBlankRows = new BooleanAtomicProperty(property, SKIP_BLANK_ROWS_DEFAULT_VALUE);
        }
        description.setSkipBlankRows(skipBlankRows);
        return description;
    }
}
