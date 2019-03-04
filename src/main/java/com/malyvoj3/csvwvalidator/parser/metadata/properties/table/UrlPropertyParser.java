package com.malyvoj3.csvwvalidator.parser.metadata.properties.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class UrlPropertyParser<T extends TableDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        LinkProperty url;
        if (property.isTextual()) {
            url = new LinkProperty(property.textValue());
        } else {
            url = null;
        }
        description.setUrl(url);
        return description;
    }

}
