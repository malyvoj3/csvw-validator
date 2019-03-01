package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class QuoteCharPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String QUOTE_CHAR_DEFAULT_VALUE = ",";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty quoteChar;
        if (property.isTextual()) {
            quoteChar = new StringAtomicProperty(property, property.textValue());
        } else if (property.isNull()) {
            quoteChar = new StringAtomicProperty(property, null);
        } else {
            quoteChar = new StringAtomicProperty(property, QUOTE_CHAR_DEFAULT_VALUE);
        }
        description.setQuoteChar(quoteChar);
        return description;
    }
}
