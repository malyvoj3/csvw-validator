package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class DoubleQuotePropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean DOUBLE_QUOTE_DEFAULT_VALUE = true;

    @Override
    public T parseProperty(T description, JsonNode property) {
        BooleanAtomicProperty doubleQuote;
        if (property.isBoolean()) {
            doubleQuote = new BooleanAtomicProperty(property.booleanValue());
        } else {
            doubleQuote = new BooleanAtomicProperty(DOUBLE_QUOTE_DEFAULT_VALUE);
        }
        description.setDoubleQuote(doubleQuote);
        return description;
    }
}
