package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.NonNull;

public class DelimiterPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String DELIMITER_DEFAULT_VALUE = ",";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty delimiter;
        if (property.isTextual()) {
            delimiter = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            delimiter = new StringAtomicProperty(DELIMITER_DEFAULT_VALUE);
        }
        description.setDelimiter(delimiter);
    }

}
