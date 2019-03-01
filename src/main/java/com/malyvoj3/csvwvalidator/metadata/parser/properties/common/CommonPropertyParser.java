package com.malyvoj3.csvwvalidator.metadata.parser.properties.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.CommonDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class CommonPropertyParser<T extends CommonDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        // TODO
        return description;
    }
}
