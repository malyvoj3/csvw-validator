package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class QuoteCharPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String QUOTE_CHAR_DEFAULT_VALUE = ",";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty quoteChar;
        if (property.isTextual()) {
            quoteChar = new StringAtomicProperty(property.textValue());
        } else if (property.isNull()) {
            quoteChar = new StringAtomicProperty(null);
        } else {
            quoteChar = new StringAtomicProperty(QUOTE_CHAR_DEFAULT_VALUE);
        }
        description.setQuoteChar(quoteChar);
        return description;
    }
}
