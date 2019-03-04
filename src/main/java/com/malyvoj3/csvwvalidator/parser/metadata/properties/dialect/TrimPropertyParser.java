package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrimPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String TRIM_DEFAULT_VALUE = "true";
    private static final Set<String> possibleValues = Stream.of("false", "true", "start", "end").collect(Collectors.toSet());

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty trim;
        if (property.isTextual() && possibleValues.contains(property.textValue())) {
            trim = new StringAtomicProperty(property.textValue());
        } else if (property.isBoolean()) {
            trim = new StringAtomicProperty(property.asText());
        } else {
            trim = new StringAtomicProperty(TRIM_DEFAULT_VALUE);
        }
        description.setTrim(trim);
        return description;
    }
}
