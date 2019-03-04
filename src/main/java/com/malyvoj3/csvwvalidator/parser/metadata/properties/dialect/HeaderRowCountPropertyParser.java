package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class HeaderRowCountPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Integer HEADER_ROW_COUNT_DEFAULT_VALUE = 0;

    @Override
    public T parseProperty(T description, JsonNode property) {
        IntegerAtomicProperty headerRowCount;
        if (property.isInt() && property.intValue() > 0) {
            headerRowCount = new IntegerAtomicProperty(property.intValue());
        } else {
            headerRowCount = new IntegerAtomicProperty(HEADER_ROW_COUNT_DEFAULT_VALUE);
        }
        description.setHeaderRowCount(headerRowCount);
        return description;
    }
}
