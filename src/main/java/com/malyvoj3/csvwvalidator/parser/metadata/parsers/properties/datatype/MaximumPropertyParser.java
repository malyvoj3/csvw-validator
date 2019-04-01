package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class MaximumPropertyParser<T extends DataTypeDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        if (property.isIntegralNumber() || property.isFloatingPointNumber() || property.isTextual()) {
            description.setMaximum(new StringAtomicProperty(property.asText()));
        } else if (!property.isNull()) {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
        }
    }
}
