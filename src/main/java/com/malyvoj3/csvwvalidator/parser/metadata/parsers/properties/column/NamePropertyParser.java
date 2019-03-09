package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class NamePropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final String INVALID_PREFIX = "_";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty name = null;
        if (property.isTextual()) {
            String stringValue = property.textValue();
            // todo URI template syntaxe
            if (!stringValue.startsWith(INVALID_PREFIX)) {
                name = new StringAtomicProperty(stringValue);
            }
            // else name is null (TODO: or should be StringAtomicProperty with null value?)
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
        }
        description.setName(name);
    }
}
