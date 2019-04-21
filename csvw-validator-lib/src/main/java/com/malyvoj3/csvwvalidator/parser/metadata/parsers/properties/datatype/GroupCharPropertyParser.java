package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class GroupCharPropertyParser<T extends FormatDescription> implements PropertyParser<T> {

    private final static String GROUP_CHAR_DEFAULT_VALUE = ".";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty groupChar;
        if (property.isTextual()) {
            groupChar = new StringAtomicProperty(property.textValue());
        } else {
            groupChar = new StringAtomicProperty(GROUP_CHAR_DEFAULT_VALUE);
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setGroupChar(groupChar);
    }
}
