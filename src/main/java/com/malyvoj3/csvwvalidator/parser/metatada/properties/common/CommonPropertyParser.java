package com.malyvoj3.csvwvalidator.parser.metatada.properties.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

public class CommonPropertyParser<T extends CommonDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        // TODO
        return description;
    }
}
