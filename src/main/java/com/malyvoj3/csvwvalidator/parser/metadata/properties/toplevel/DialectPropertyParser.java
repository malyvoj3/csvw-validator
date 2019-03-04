package com.malyvoj3.csvwvalidator.parser.metadata.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.DialectDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class DialectPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private DialectDescriptionParser dialectDescriptionParser = new DialectDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ObjectProperty<DialectDescription> dialect;
        if (property.isObject()) {
            DialectDescription dialectDescription = dialectDescriptionParser.parse((ObjectNode) property);
            dialect = new ObjectProperty<>(dialectDescription);
        } else if (property.isTextual() && isUrl(property.textValue())) {
            dialect = new ObjectProperty<>(property.textValue(), dialectDescriptionParser);
        } else {
            dialect = null;
        }
        description.setDialect(dialect);
        return description;
    }

    private boolean isUrl(String textValue) {
        // TODO
        return true;
    }

}
