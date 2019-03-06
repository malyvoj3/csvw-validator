package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class EncodingPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String ENCODING_DEFAULT_VALUE = "utf-8";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty encoding;
        if (property.isTextual() && isValidEncoding()) {
            encoding = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            encoding = new StringAtomicProperty(ENCODING_DEFAULT_VALUE);
        }
        description.setEncoding(encoding);
    }

    private boolean isValidEncoding() {
        // TODO
        return true;
    }
}
