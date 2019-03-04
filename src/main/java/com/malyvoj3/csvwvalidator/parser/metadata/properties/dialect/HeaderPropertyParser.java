package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class HeaderPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean HEADER_DEFAULT_VALUE = true;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty header;
        if (property.isBoolean()) {
            header = new BooleanAtomicProperty(property.booleanValue());
        } else {
            header = new BooleanAtomicProperty(HEADER_DEFAULT_VALUE);
        }
        description.setHeader(header);
        return description;
    }
}
