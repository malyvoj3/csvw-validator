package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class DelimiterPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String DELIMITER_DEFAULT_VALUE = ",";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty delimiter;
        if (property.isTextual()) {
            delimiter = new StringAtomicProperty(property.textValue());
        } else {
            delimiter = new StringAtomicProperty(DELIMITER_DEFAULT_VALUE);
        }
        description.setDelimiter(delimiter);
        return description;
    }
}
