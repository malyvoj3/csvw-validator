package com.malyvoj3.csvwvalidator.metadata.parser.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.SchemaDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class TableSchemaPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private SchemaDescriptionParser schemaDescriptionParser = new SchemaDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ObjectProperty<SchemaDescription> tableSchema;
        if (property.isObject()) {
            SchemaDescription schemaDescription = schemaDescriptionParser.parse((ObjectNode) property);
            tableSchema = new ObjectProperty<>(property, schemaDescription);
        } else if (property.isTextual() && isUrl(property.textValue())) {
            tableSchema = new ObjectProperty<>(property, property.textValue());
        } else {
            tableSchema = null;
        }
        description.setTableSchema(tableSchema);
        return description;
    }

    private boolean isUrl(String textValue) {
        // TODO
        return true;
    }

}

