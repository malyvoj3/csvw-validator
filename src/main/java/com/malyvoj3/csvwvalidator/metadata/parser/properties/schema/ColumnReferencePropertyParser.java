package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnReferencePropertyParser<T extends ForeignKeyDescription> implements PropertyParser<T> {

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
