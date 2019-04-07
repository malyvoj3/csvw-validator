package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.ColumnDescriptionParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class ColumnsPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    private final ColumnDescriptionParser columnDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        ArrayProperty<ColumnDescription> columns = null;
        List<ColumnDescription> columnsList = new ArrayList<>();
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            int arrayCounter = 0;
            for (Iterator<JsonNode> iter = arrayNode.elements(); iter.hasNext(); arrayCounter++) {
                JsonNode element = iter.next();
                if (element.isObject()) {
                    JsonObject jsonObject = new JsonObject(String.valueOf(arrayCounter), (ObjectNode) element);
                    ColumnDescription columnDesc = columnDescriptionParser.parse(jsonObject);
                    if (columnDesc != null) {
                        columnsList.add(columnDesc);
                    }
                    jsonObject.getParsingErrors().forEach(error -> {
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
        if (!columnsList.isEmpty()) {
            columns = new ArrayProperty<>(columnsList);
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setColumns(columns);
    }
}
