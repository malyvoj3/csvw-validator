package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RowTitlesPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        ColumnReferenceProperty rowTitles;
        if (property.isTextual()) {
            rowTitles = new ColumnReferenceProperty(Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            List<String> rowTitlesList = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    rowTitlesList.add(jsonNode.textValue());
                }
            });
            rowTitles = new ColumnReferenceProperty(rowTitlesList);
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            rowTitles = null;
        }
        description.setRowTitles(rowTitles);
    }
}
