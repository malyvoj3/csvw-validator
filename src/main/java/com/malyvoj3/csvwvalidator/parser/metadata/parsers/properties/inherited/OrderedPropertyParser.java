package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class OrderedPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final Boolean ORDERED_DEFAULT_VALUE = false;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty ordered;
        if (property.isBoolean()) {
            ordered = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            ordered = new BooleanAtomicProperty(ORDERED_DEFAULT_VALUE);
        }
        description.setOrdered(ordered);
    }

}
