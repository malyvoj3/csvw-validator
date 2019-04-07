package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.NonNull;

public class DecimalCharPropertyParser<T extends FormatDescription> implements PropertyParser<T> {

    private final static String DECIMAL_CHAR_DEFAULT_VALUE = null;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty decimalChar;
        if (property.isTextual()) {
            decimalChar = new StringAtomicProperty(property.textValue());
        } else {
            decimalChar = new StringAtomicProperty(DECIMAL_CHAR_DEFAULT_VALUE);
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setDecimalChar(decimalChar);
    }
}
