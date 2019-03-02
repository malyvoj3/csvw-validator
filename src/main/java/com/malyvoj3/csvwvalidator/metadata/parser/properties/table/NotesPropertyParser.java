package com.malyvoj3.csvwvalidator.metadata.parser.properties.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class NotesPropertyParser<T extends TableDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        // TODO
        return null;
    }
}
