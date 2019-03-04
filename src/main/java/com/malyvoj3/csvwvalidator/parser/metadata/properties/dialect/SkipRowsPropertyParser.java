package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class SkipRowsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Integer SKIP_ROWS_DEFAULT_VALUE = 0;

    @Override
    public T parseProperty(T description, JsonNode property) {
        IntegerAtomicProperty skipRows;
        if (property.isInt() && property.intValue() > 0) {
            skipRows = new IntegerAtomicProperty(property.intValue());
        } else {
            skipRows = new IntegerAtomicProperty(SKIP_ROWS_DEFAULT_VALUE);
        }
        description.setSkipRows(skipRows);
        return description;
    }
}
