package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class SkipBlankRowsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final boolean SKIP_BLANK_ROWS_DEFAULT_VALUE = false;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        BooleanAtomicProperty skipBlankRows;
        if (property.isBoolean()) {
            skipBlankRows = new BooleanAtomicProperty(property.booleanValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            skipBlankRows = new BooleanAtomicProperty(SKIP_BLANK_ROWS_DEFAULT_VALUE);
        }
        description.setSkipBlankRows(skipBlankRows);
    }

}
