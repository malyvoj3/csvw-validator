package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class DoubleQuotePropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean DOUBLE_QUOTE_DEFAULT_VALUE = true;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty doubleQuote;
        if (property.isBoolean()) {
            doubleQuote = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            doubleQuote = new BooleanAtomicProperty(DOUBLE_QUOTE_DEFAULT_VALUE);
        }
        description.setDoubleQuote(doubleQuote);
    }

}
