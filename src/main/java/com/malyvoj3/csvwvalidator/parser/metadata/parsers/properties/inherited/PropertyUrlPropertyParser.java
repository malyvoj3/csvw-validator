package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.UriTemplateProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.NonNull;

public class PropertyUrlPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String PROPERTY_URL_DEFAULT_VALUE = "";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        UriTemplateProperty propertyUrl;
        if (property.isTextual()) {
            propertyUrl = new UriTemplateProperty(property.textValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            propertyUrl = new UriTemplateProperty(PROPERTY_URL_DEFAULT_VALUE);
        }
        description.setPropertyUrl(propertyUrl);
    }
}
