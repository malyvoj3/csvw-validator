package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

public class BasePropertyParser<T extends DataTypeDescription> implements PropertyParser<T> {

    private final static String BASE_DEFAULT_VALUE = "string";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty base;
        if (property.isTextual() && CsvwKeywords.DATA_TYPES.get(property.textValue()) != null) {
            base = new StringAtomicProperty(property.textValue());
        } else {
            base = new StringAtomicProperty(BASE_DEFAULT_VALUE);
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
        }
        description.setBase(base);
    }

}
