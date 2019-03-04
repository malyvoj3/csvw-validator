package com.malyvoj3.csvwvalidator.parser.metadata.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class NotesPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        // TODO
        return null;
    }
}
