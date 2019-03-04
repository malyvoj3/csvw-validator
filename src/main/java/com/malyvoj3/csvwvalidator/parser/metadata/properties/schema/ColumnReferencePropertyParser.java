package com.malyvoj3.csvwvalidator.parser.metadata.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnReferencePropertyParser<T extends ForeignKeyDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
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
            columnReference = null;
        }
        description.setColumnReference(columnReference);
        return description;
    }
}
