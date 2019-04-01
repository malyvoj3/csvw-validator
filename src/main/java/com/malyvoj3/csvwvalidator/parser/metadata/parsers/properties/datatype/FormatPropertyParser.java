package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.AtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.FormatDescriptionParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FormatPropertyParser<T extends DataTypeDescription> implements PropertyParser<T> {

    private final FormatDescriptionParser formatDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        AtomicProperty<FormatDescription> format;
        if (property.isObject()) {
            JsonObject jsonObject = new JsonObject(jsonProperty.getName(), (ObjectNode) property);
            FormatDescription formatDescription = formatDescriptionParser.parse(jsonObject);
            jsonObject.getParsingErrors().forEach(jsonProperty::addError);
            format = new AtomicProperty<>(formatDescription);
        } else if (property.isTextual()) {
            FormatDescription formatDescription = new FormatDescription();
            formatDescription.setPattern(new StringAtomicProperty(property.textValue()));
            format = new AtomicProperty<>(formatDescription);
        } else {
            format = null;
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
        }
        description.setFormat(format);
    }
}
