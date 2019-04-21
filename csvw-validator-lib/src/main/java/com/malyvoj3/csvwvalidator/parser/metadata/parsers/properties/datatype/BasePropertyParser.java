package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class BasePropertyParser<T extends DataTypeDescription> implements PropertyParser<T> {

    private final static String BASE_DEFAULT_VALUE = "string";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty base;
        if (property.isTextual() && DataTypeDefinition.getByName(property.textValue()) != null) {
            base = new StringAtomicProperty(property.textValue());
        } else if (property.isTextual() && DataTypeDefinition.getByUrl(property.textValue()) != null) {
            base = new StringAtomicProperty(DataTypeDefinition.getByUrl(property.textValue()).getName());
        } else {
            base = new StringAtomicProperty(BASE_DEFAULT_VALUE);
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setBase(base);
    }

}
