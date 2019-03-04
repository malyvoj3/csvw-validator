package com.malyvoj3.csvwvalidator.parser.metadata.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class LangPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String LANG_DEFAULT_VALUE = "und";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty langProperty;
        if (property.isTextual() && isLanguageCode()) {
            langProperty = new StringAtomicProperty(property.textValue());
        } else {
            langProperty = new StringAtomicProperty(LANG_DEFAULT_VALUE);
        }
        description.setLang(langProperty);
        return description;
    }

    private boolean isLanguageCode() {
        // TODO language codes
        return true;
    }
}
