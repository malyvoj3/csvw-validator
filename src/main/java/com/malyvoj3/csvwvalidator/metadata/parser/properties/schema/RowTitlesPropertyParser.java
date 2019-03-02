package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.metadata.domain.SchemaDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RowTitlesPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        ColumnReferenceProperty rowTitles;
        if (property.isTextual()) {
            rowTitles = new ColumnReferenceProperty(property, Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            List<String> rowTitlesList = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    rowTitlesList.add(jsonNode.textValue());
                }
            });
            rowTitles = new ColumnReferenceProperty(property, rowTitlesList);
        } else {
            rowTitles = null;
        }
        description.setPrimaryKey(rowTitles);
        return description;
    }
}
