package com.malyvoj3.csvwvalidator.parser.metadata.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimaryKeyPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        ColumnReferenceProperty primaryKey;
        if (property.isTextual()) {
            primaryKey = new ColumnReferenceProperty(Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            List<String> primaryKeysList = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    primaryKeysList.add(jsonNode.textValue());
                }
            });
            primaryKey = new ColumnReferenceProperty(primaryKeysList);
        } else {
            primaryKey = null;
        }
        description.setPrimaryKey(primaryKey);
        return description;
    }
}
