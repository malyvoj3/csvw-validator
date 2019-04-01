package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class SkipRowsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Long SKIP_ROWS_DEFAULT_VALUE = 0L;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        IntegerAtomicProperty skipRows;
        if (property.isIntegralNumber() && property.longValue() > 0) {
            skipRows = new IntegerAtomicProperty(property.longValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            skipRows = new IntegerAtomicProperty(SKIP_ROWS_DEFAULT_VALUE);
        }
        description.setSkipRows(skipRows);
    }
}
