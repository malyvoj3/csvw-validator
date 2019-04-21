package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ReferenceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class SchemaReferencePropertyParser<T extends ReferenceDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        LinkProperty schemaReference;
        if (property.isTextual()) {
            schemaReference = new LinkProperty(property.textValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            schemaReference = null;
        }
        description.setSchemaReference(schemaReference);
    }
}
