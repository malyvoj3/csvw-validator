package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.tablegroup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableDescriptionParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class TablesPropertyParser<T extends TableGroupDescription> implements PropertyParser<T> {

    private final TableDescriptionParser tableDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();

        ArrayProperty<TableDescription> tables = null;
        List<TableDescription> tablesList = new ArrayList<>();
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            int arrayCounter = 0;
            for (Iterator<JsonNode> iter = arrayNode.elements(); iter.hasNext(); arrayCounter++) {
                JsonNode element = iter.next();
                if (element.isObject()) {
                    JsonObject jsonObject = new JsonObject(String.valueOf(arrayCounter), (ObjectNode) element);
                    TableDescription tableDesc = tableDescriptionParser.parse(jsonObject);
                    if (tableDesc != null) {
                        tablesList.add(tableDesc);
                    }
                    jsonObject.getErrors().forEach(error -> {
                        error.addKey(jsonProperty.getName());
                        jsonProperty.addError(error);
                    });
                } else {
                    JsonParserError error = JsonParserError.invalidPropertyType(String.valueOf(arrayCounter));
                    error.addKey(jsonProperty.getName());
                    jsonProperty.addError(error);
                }
            }
        }
        if (!tablesList.isEmpty()) {
            tables = new ArrayProperty<>(tablesList);
        }
        description.setTables(tables);
    }

}
