package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ReferenceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class SchemaReferencePropertyParser<T extends ReferenceDescription> implements PropertyParser<T> {
    @Override
    public T parseProperty(T description, JsonNode property) {
        LinkProperty schemaReference;
        if (property.isTextual()) {
            schemaReference = new LinkProperty(property, property.textValue());
        } else {
            schemaReference = null;
        }
        description.setSchemaReference(schemaReference);
        return description;
    }
}
