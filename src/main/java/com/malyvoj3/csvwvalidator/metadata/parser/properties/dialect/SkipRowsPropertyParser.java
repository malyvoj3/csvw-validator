package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class SkipRowsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Integer SKIP_ROWS_DEFAULT_VALUE = 0;

    @Override
    public T parseProperty(T description, JsonNode property) {
        IntegerAtomicProperty skipRows;
        if (property.isInt() && property.intValue() > 0) {
            skipRows = new IntegerAtomicProperty(property, property.intValue());
        } else {
            skipRows = new IntegerAtomicProperty(property, SKIP_ROWS_DEFAULT_VALUE);
        }
        description.setSkipRows(skipRows);
        return description;
    }
}
