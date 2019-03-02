package com.malyvoj3.csvwvalidator.metadata.parser.properties.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class UrlPropertyParser<T extends TableDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        LinkProperty url;
        if (property.isTextual()) {
            url = new LinkProperty(property, property.textValue());
        } else {
            url = null;
        }
        description.setUrl(url);
        return description;
    }

}
