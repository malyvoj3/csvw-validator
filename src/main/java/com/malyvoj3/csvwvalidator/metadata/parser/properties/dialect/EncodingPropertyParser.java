package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class EncodingPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String ENCODING_DEFAULT_VALUE = "utf-8";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty encoding;
        if (property.isTextual() && isValidEncoding()) {
            encoding = new StringAtomicProperty(property, property.textValue());
        } else {
            encoding = new StringAtomicProperty(property, ENCODING_DEFAULT_VALUE);
        }
        description.setEncoding(encoding);
        return description;
    }

    private boolean isValidEncoding() {
        // TODO
        return true;
    }
}
