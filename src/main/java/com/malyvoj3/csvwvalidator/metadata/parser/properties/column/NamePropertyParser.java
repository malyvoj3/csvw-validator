package com.malyvoj3.csvwvalidator.metadata.parser.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ColumnDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class NamePropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final String INVALID_PREFIX = "_";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty name = null;
        if (property.isTextual()) {
            String stringValue = property.textValue();
            if (!stringValue.startsWith(INVALID_PREFIX)) {
                name = new StringAtomicProperty(property, stringValue);
            }
            // else name is null (TODO: or should be StringAtomicProperty with null value?)
        }
        description.setName(name);
        return description;
    }

}
