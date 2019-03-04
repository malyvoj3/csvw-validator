package com.malyvoj3.csvwvalidator.parser.metadata.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class TableSchemaPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private SchemaDescriptionParser schemaDescriptionParser = new SchemaDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ObjectProperty<SchemaDescription> tableSchema;
        if (property.isObject()) {
            SchemaDescription schemaDescription = schemaDescriptionParser.parse((ObjectNode) property);
            tableSchema = new ObjectProperty<>(schemaDescription);
        } else if (property.isTextual() && isUrl(property.textValue())) {
            tableSchema = new ObjectProperty<>(property.textValue(), schemaDescriptionParser);
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

