package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ReferenceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class ResourcePropertyParser<T extends ReferenceDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        LinkProperty resource;
        if (property.isTextual()) {
            resource = new LinkProperty(property, property.textValue());
        } else {
            resource = null;
        }
        description.setResource(resource);
        return description;
    }
}
