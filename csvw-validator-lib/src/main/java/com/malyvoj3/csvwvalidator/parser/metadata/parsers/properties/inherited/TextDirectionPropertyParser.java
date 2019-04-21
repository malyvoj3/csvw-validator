package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextDirectionPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String TEXT_DIRECTION_DEFAULT_VALUE = "inherit";
    private static final Set<String> possibleValues = Stream.of("auto", "inherit", "ltr", "rtl").collect(Collectors.toSet());

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty textDirection;
        if (property.isTextual() && possibleValues.contains(property.textValue())) {
            textDirection = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            textDirection = new StringAtomicProperty(TEXT_DIRECTION_DEFAULT_VALUE);
        }
        description.setTextDirection(textDirection);
    }
}
