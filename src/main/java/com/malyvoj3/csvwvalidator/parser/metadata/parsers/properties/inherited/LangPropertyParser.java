package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class LangPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String LANG_DEFAULT_VALUE = "und";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty langProperty;
        if (property.isTextual() && isLanguageCode()) {
            langProperty = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            langProperty = new StringAtomicProperty(LANG_DEFAULT_VALUE);
        }
        description.setLang(langProperty);
    }

    private boolean isLanguageCode() {
        // TODO language codes
        return true;
    }
}
