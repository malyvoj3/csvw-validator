package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableDirectionPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    private static final String TABLE_DIRECTION_DEFAULT_VALUE = "auto";
    private static final Set<String> possibleValues = Stream.of("auto", "ltr", "rtl").collect(Collectors.toSet());

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty textDirection;
        if (property.isTextual() && possibleValues.contains(property.textValue())) {
            textDirection = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            textDirection = new StringAtomicProperty(TABLE_DIRECTION_DEFAULT_VALUE);
        }
        description.setTableDirection(textDirection);
    }
}
