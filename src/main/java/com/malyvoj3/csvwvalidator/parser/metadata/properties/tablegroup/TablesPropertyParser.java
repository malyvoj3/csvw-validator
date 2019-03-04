package com.malyvoj3.csvwvalidator.parser.metadata.properties.tablegroup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

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
            tables = new ArrayProperty<>(tablesList);
        }
        description.setTables(tables);
        return null;
    }

}
