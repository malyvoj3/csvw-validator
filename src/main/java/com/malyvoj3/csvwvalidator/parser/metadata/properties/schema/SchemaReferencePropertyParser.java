package com.malyvoj3.csvwvalidator.parser.metadata.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ReferenceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class SchemaReferencePropertyParser<T extends ReferenceDescription> implements PropertyParser<T> {
    @Override
    public T parseProperty(T description, JsonNode property) {
        LinkProperty schemaReference;
        if (property.isTextual()) {
            schemaReference = new LinkProperty(property.textValue());
        } else {
            schemaReference = null;
        }
        description.setSchemaReference(schemaReference);
        return description;
    }
}
