package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.NonNull;

public class SeparatorPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String SEPARATOR_DEFAULT_VALUE = null;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty separator;
        if (property.isTextual()) {
            separator = new StringAtomicProperty(property.textValue());
        } else {
            separator = new StringAtomicProperty(SEPARATOR_DEFAULT_VALUE);
            if (!property.isNull()) {
                jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            }
        }
        description.setSeparator(separator);
    }
}
