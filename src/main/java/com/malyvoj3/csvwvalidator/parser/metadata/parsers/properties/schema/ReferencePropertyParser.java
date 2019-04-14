package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ReferenceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.ReferenceDescriptionParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReferencePropertyParser<T extends ForeignKeyDescription> implements PropertyParser<T> {

    private final ReferenceDescriptionParser referenceDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        ObjectProperty<ReferenceDescription> reference;
        if (property.isObject()) {
            JsonObject jsonObject = new JsonObject(jsonProperty.getName(), (ObjectNode) property);
            ReferenceDescription referenceDescription = referenceDescriptionParser.parse(jsonObject);
            jsonObject.getParsingErrors().forEach(jsonProperty::addError);
            reference = new ObjectProperty<>(referenceDescription);
        } else if (property.isTextual()) {
            reference = new ObjectProperty<>(property.textValue(), referenceDescriptionParser);
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            reference = null;
        }
        description.setReference(reference);
    }

}
