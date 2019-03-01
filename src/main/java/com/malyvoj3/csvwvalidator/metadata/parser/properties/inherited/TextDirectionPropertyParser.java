package com.malyvoj3.csvwvalidator.metadata.parser.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.InheritanceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextDirectionPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String TEXT_DIRECTION_DEFAULT_VALUE = "inherit";
    private static final Set<String> possibleValues = Stream.of("auto", "inherit", "ltr", "rtl").collect(Collectors.toSet());

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty textDirection;
        if (property.isTextual() && possibleValues.contains(property.textValue())) {
            textDirection = new StringAtomicProperty(property, property.textValue());
        } else {
            textDirection = new StringAtomicProperty(property, TEXT_DIRECTION_DEFAULT_VALUE);
        }
        description.setTextDirection(textDirection);
        return description;
    }
}
