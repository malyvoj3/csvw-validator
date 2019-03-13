package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TableSchemaPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    private final SchemaDescriptionParser schemaDescriptionParser;

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        ObjectProperty<SchemaDescription> tableSchema;
        if (property.isObject()) {
            JsonObject jsonObject = new JsonObject(jsonProperty.getName(), (ObjectNode) property);
            SchemaDescription schemaDescription = schemaDescriptionParser.parse(jsonObject);
            jsonObject.getParsingErrors().forEach(jsonProperty::addError);
            tableSchema = new ObjectProperty<>(schemaDescription);
        } else if (property.isTextual()) {
            tableSchema = new ObjectProperty<>(property.textValue(), schemaDescriptionParser);
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            tableSchema = null;
        }
        description.setTableSchema(tableSchema);
    }

}

