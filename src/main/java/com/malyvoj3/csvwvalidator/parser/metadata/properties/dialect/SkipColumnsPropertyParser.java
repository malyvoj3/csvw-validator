package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class SkipColumnsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Integer SKIP_COLUMNS_DEFAULT_VALUE = 0;

    @Override
    public T parseProperty(T description, JsonNode property) {
        IntegerAtomicProperty skipColumns;
        if (property.isInt() && property.intValue() > 0) {
            skipColumns = new IntegerAtomicProperty(property.intValue());
        } else {
            skipColumns = new IntegerAtomicProperty(SKIP_COLUMNS_DEFAULT_VALUE);
        }
        description.setSkipColumns(skipColumns);
        return description;
    }
}
