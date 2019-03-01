package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class DelimiterPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String DELIMITER_DEFAULT_VALUE = ",";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty delimiter;
        if (property.isTextual()) {
            delimiter = new StringAtomicProperty(property, property.textValue());
        } else {
            delimiter = new StringAtomicProperty(property, DELIMITER_DEFAULT_VALUE);
        }
        description.setDelimiter(delimiter);
        return description;
    }
}
