package com.malyvoj3.csvwvalidator.metadata.parser.properties.tablegroup;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class TablesPropertyParser<T extends TableGroupDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        return null;
    }

}
