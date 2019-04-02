package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.AtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DataTypeDescriptionParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataTypePropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private final DataTypeDescriptionParser dataTypeDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        DataTypeDescription dataType;
        if (property.isObject()) {
            JsonObject jsonObject = new JsonObject(jsonProperty.getName(), (ObjectNode) property);
            dataType = dataTypeDescriptionParser.parse(jsonObject);
            jsonObject.getParsingErrors().forEach(jsonProperty::addError);
        } else if (property.isTextual() && CsvwKeywords.DATA_TYPES.get(property.textValue()) != null) {
            dataType = new DataTypeDescription();
            dataType.setBase(new StringAtomicProperty(property.textValue()));
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            dataType = null;
        }
        if (dataType != null) {
            description.setDataType(new AtomicProperty<>(dataType));
        }
    }

}
