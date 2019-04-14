package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ReferenceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: stejne jak columnReference jen pro jiny typ, udelat napr. spolecneho predka?
public class ReferenceColumnReferencePropertyParser<T extends ReferenceDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        ColumnReferenceProperty columnReference;
        if (property.isTextual()) {
            columnReference = new ColumnReferenceProperty(Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            List<String> columnReferenceList = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    columnReferenceList.add(jsonNode.textValue());
                }
            });
            columnReference = new ColumnReferenceProperty(columnReferenceList);
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            columnReference = null;
        }
        description.setColumnReference(columnReference);
    }
}
