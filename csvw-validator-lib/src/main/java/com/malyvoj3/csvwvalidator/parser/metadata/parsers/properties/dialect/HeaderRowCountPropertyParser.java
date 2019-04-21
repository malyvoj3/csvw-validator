package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class HeaderRowCountPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Long HEADER_ROW_COUNT_DEFAULT_VALUE = 0L;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        IntegerAtomicProperty headerRowCount;
        if (property.isIntegralNumber() && property.longValue() > 0) {
            headerRowCount = new IntegerAtomicProperty(property.longValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            headerRowCount = new IntegerAtomicProperty(HEADER_ROW_COUNT_DEFAULT_VALUE);
        }
        description.setHeaderRowCount(headerRowCount);
    }

}
