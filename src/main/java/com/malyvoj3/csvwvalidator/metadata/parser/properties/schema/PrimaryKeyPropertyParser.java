package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.metadata.domain.SchemaDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimaryKeyPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        ColumnReferenceProperty primaryKey;
        if (property.isTextual()) {
            primaryKey = new ColumnReferenceProperty(property, Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            List<String> primaryKeysList = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    primaryKeysList.add(jsonNode.textValue());
                }
            });
            primaryKey = new ColumnReferenceProperty(property, primaryKeysList);
        } else {
            primaryKey = null;
        }
        description.setPrimaryKey(primaryKey);
        return description;
    }
}
