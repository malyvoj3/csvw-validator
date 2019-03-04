package com.malyvoj3.csvwvalidator.parser.metadata.properties.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class CommonPropertyParser<T extends CommonDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        // TODO
        return description;
    }
}
