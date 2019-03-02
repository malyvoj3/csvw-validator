package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ReferenceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: stejne jak columnReference jen pro jiny typ, udelat napr. spolecneho predka?
public class ReferenceColumnReferencePropertyParser<T extends ReferenceDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        ColumnReferenceProperty columnReference;
        if (property.isTextual()) {
            columnReference = new ColumnReferenceProperty(property, Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            List<String> columnReferenceList = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    columnReferenceList.add(jsonNode.textValue());
                }
            });
            columnReference = new ColumnReferenceProperty(property, columnReferenceList);
        } else {
            columnReference = null;
        }
        description.setColumnReference(columnReference);
        return description;
    }
}
