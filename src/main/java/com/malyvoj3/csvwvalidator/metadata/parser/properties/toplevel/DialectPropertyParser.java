package com.malyvoj3.csvwvalidator.metadata.parser.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.DialectDescriptionParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class DialectPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private DialectDescriptionParser dialectDescriptionParser = new DialectDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ObjectProperty<DialectDescription> dialect;
        if (property.isObject()) {
            DialectDescription dialectDescription = dialectDescriptionParser.parse((ObjectNode) property);
            dialect = new ObjectProperty<>(property, dialectDescription);
        } else if (property.isTextual() && isUrl(property.textValue())) {
            dialect = new ObjectProperty<>(property, property.textValue());
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
