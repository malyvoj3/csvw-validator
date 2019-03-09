package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.UriTemplateProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class AboutUrlPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String ABOUT_URL_DEFAULT_VALUE = "";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        UriTemplateProperty aboutUrl;
        if (property.isTextual()) {
            // String value asi neni potreba?
            aboutUrl = new UriTemplateProperty(property.textValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            aboutUrl = new UriTemplateProperty(ABOUT_URL_DEFAULT_VALUE);
        }
        description.setAboutUrl(aboutUrl);
    }

}
