package com.malyvoj3.csvwvalidator.parser.metadata.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.ColumnDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

import java.util.ArrayList;
import java.util.List;

public class ColumnsPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private ColumnDescriptionParser columnDescriptionParser = new ColumnDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ArrayProperty<ColumnDescription> columns = null;
        List<ColumnDescription> columnsList = new ArrayList<>();
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                ColumnDescription columnDesc = columnDescriptionParser.parse((ObjectNode) jsonNode);
                if (columnDesc != null) {
                    columnsList.add(columnDesc);
                }
            });
        }
        if (!columnsList.isEmpty()) {
            columns = new ArrayProperty<>(columnsList);
        }
        description.setColumns(columns);
        return null;
    }
}
