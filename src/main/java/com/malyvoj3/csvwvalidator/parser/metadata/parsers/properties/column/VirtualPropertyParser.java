package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class VirtualPropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final Boolean VIRTUAL_DEFAULT_VALUE = false;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty virtual;
        if (property.isBoolean()) {
            virtual = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            virtual = new BooleanAtomicProperty(VIRTUAL_DEFAULT_VALUE);
        }
        description.setSuppressOutput(virtual);
    }

}
