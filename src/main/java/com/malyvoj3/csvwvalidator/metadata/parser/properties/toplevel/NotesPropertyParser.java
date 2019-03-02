package com.malyvoj3.csvwvalidator.metadata.parser.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class NotesPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        // TODO
        return null;
    }
}
