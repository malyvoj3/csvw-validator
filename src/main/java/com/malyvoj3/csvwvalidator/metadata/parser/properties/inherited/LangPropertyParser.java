package com.malyvoj3.csvwvalidator.metadata.parser.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.InheritanceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class LangPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String LANG_DEFAULT_VALUE = "und";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty langProperty;
        if (property.isTextual() && isLanguageCode()) {
            langProperty = new StringAtomicProperty(property, property.textValue());
        } else {
            langProperty = new StringAtomicProperty(property, LANG_DEFAULT_VALUE);
        }
        description.setLang(langProperty);
        return description;
    }

    private boolean isLanguageCode() {
        // TODO language codes
        return true;
    }
}
