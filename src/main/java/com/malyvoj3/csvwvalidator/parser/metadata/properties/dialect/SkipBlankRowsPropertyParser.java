package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class SkipBlankRowsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean SKIP_BLANK_ROWS_DEFAULT_VALUE = false;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty skipBlankRows;
        if (property.isBoolean()) {
            skipBlankRows = new BooleanAtomicProperty(property.booleanValue());
        } else {
            skipBlankRows = new BooleanAtomicProperty(SKIP_BLANK_ROWS_DEFAULT_VALUE);
        }
        description.setSkipBlankRows(skipBlankRows);
        return description;
    }
}
