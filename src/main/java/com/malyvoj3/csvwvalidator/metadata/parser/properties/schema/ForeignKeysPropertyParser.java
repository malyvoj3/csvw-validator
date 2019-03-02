package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.SchemaDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.ForeignKeyDescriptionParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.ArrayList;
import java.util.List;

public class ForeignKeysPropertyParser<T extends SchemaDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private ForeignKeyDescriptionParser foreignKeyDescriptionParser = new ForeignKeyDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ArrayProperty<ForeignKeyDescription> foreignKeys = null;
        List<ForeignKeyDescription> foreignKeyList = new ArrayList<>();
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            arrayNode.elements().forEachRemaining(jsonNode -> {
                ForeignKeyDescription foreignKeyDesc = foreignKeyDescriptionParser.parse((ObjectNode) jsonNode);
                if (foreignKeyDesc != null) {
                    foreignKeyList.add(foreignKeyDesc);
                }
            });
        }
        if (!foreignKeyList.isEmpty()) {
            foreignKeys = new ArrayProperty<>(property, foreignKeyList);
        }
        description.setForeignKeys(foreignKeys);
        return null;
    }

}
