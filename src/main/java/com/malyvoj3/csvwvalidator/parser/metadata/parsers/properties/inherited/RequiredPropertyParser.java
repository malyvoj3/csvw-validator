package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class RequiredPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final Boolean REQUIRED_DEFAULT_VALUE = false;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty required;
        if (property.isBoolean()) {
            required = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            required = new BooleanAtomicProperty(REQUIRED_DEFAULT_VALUE);
        }
        description.setRequired(required);
    }
}
