package com.malyvoj3.csvwvalidator.parser.metadata.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ContextParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class ContextPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    private ContextParser parser = new ContextParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        Context context = parser.parse(property);
        description.setContext(context);
        return description;
    }

}
