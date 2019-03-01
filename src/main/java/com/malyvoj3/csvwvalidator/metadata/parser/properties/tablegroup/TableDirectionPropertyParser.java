package com.malyvoj3.csvwvalidator.metadata.parser.properties.tablegroup;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableDirectionPropertyParser<T extends TableGroupDescription> implements PropertyParser<T> {

    private static final String TABLE_DIRECTION_DEFAULT_VALUE = "auto";
    private static final Set<String> possibleValues = Stream.of("auto", "ltr", "rtl").collect(Collectors.toSet());

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty textDirection;
        if (property.isTextual() && possibleValues.contains(property.textValue())) {
            textDirection = new StringAtomicProperty(property, property.textValue());
        } else {
            textDirection = new StringAtomicProperty(property, TABLE_DIRECTION_DEFAULT_VALUE);
        }
        description.setTextDirection(textDirection);
        return description;
    }
}
