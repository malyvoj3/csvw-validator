package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class SuppressOutputPropertyParser<T extends TableDescription> implements PropertyParser<T> {

    private static final Boolean SUPPRESS_OUTPUT_DEFAULT_VALUE = false;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty suppressOutput;
        if (property.isBoolean()) {
            suppressOutput = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            suppressOutput = new BooleanAtomicProperty(SUPPRESS_OUTPUT_DEFAULT_VALUE);
        }
        description.setSuppressOutput(suppressOutput);
    }

}
