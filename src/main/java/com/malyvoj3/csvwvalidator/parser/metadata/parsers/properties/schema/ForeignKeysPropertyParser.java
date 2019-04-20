package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.ForeignKeyDescriptionParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class ForeignKeysPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    private final ForeignKeyDescriptionParser foreignKeyDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();

        ArrayProperty<ForeignKeyDescription> foreignKeys = null;
        List<ForeignKeyDescription> foreignKeyList = new ArrayList<>();
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            int arrayCounter = 0;
            for (Iterator<JsonNode> iter = arrayNode.elements(); iter.hasNext(); arrayCounter++) {
                JsonNode element = iter.next();
                if (element.isObject()) {
                    JsonObject jsonObject = new JsonObject(String.valueOf(arrayCounter), (ObjectNode) element);
                    ForeignKeyDescription foreignKeyDesc = foreignKeyDescriptionParser.parse(jsonObject);
                    if (foreignKeyDesc != null) {
                        foreignKeyList.add(foreignKeyDesc);
                    }
                    jsonObject.getErrors().forEach(error -> {
                        error.addKey(jsonProperty.getName());
                        jsonProperty.addError(error);
                    });
                } else {
                    JsonParserError error = JsonParserError.invalidPropertyType(String.valueOf(arrayCounter));
                    error.addKey(jsonProperty.getName());
                    jsonProperty.addError(error);
                }
            }
        }
        if (!foreignKeyList.isEmpty()) {
            foreignKeys = new ArrayProperty<>(foreignKeyList);
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setForeignKeys(foreignKeys);

    }
}
