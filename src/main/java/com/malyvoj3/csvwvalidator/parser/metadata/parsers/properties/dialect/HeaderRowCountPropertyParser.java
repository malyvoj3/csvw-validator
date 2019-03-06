package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class HeaderRowCountPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final Integer HEADER_ROW_COUNT_DEFAULT_VALUE = 0;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        IntegerAtomicProperty headerRowCount;
        if (property.isInt() && property.intValue() > 0) {
            headerRowCount = new IntegerAtomicProperty(property.intValue());
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            headerRowCount = new IntegerAtomicProperty(HEADER_ROW_COUNT_DEFAULT_VALUE);
        }
        description.setHeaderRowCount(headerRowCount);
    }

}
