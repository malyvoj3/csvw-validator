package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class SkipColumnsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Long SKIP_COLUMNS_DEFAULT_VALUE = 0L;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        IntegerAtomicProperty skipColumns;
        if (property.isIntegralNumber() && property.longValue() > 0) {
            skipColumns = new IntegerAtomicProperty(property.longValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            skipColumns = new IntegerAtomicProperty(SKIP_COLUMNS_DEFAULT_VALUE);
        }
        description.setSkipColumns(skipColumns);
    }
}
