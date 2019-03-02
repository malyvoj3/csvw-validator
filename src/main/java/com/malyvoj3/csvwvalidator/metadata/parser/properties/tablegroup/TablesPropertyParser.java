package com.malyvoj3.csvwvalidator.metadata.parser.properties.tablegroup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.ArrayList;
import java.util.List;

public class TablesPropertyParser<T extends TableGroupDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private TableDescriptionParser tableDescriptionParser = new TableDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ArrayProperty<TableDescription> tables = null;
        List<TableDescription> tablesList = new ArrayList<>();
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                TableDescription tableDesc = tableDescriptionParser.parse((ObjectNode) jsonNode);
                if (tableDesc != null) {
                    tablesList.add(tableDesc);
                }
            });
        }
        if (!tablesList.isEmpty()) {
            tables = new ArrayProperty<>(property, tablesList);
        }
        description.setTables(tables);
        return null;
    }

}
