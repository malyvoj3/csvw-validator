package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class SkipInitialSpacePropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean SKIP_INITIAL_SPACE_DEFAULT_VALUE = false;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty skipInitialSpace;
        if (property.isBoolean()) {
            skipInitialSpace = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            skipInitialSpace = new BooleanAtomicProperty(SKIP_INITIAL_SPACE_DEFAULT_VALUE);
        }
        description.setSkipInitialSpace(skipInitialSpace);
    }

}
