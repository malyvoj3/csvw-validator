package com.malyvoj3.csvwvalidator.metadata.parser.properties.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.ReferenceDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.ReferenceDescriptionParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class ReferencePropertyParser<T extends ForeignKeyDescription> implements PropertyParser<T> {

    // TODO spring bean?
    private ReferenceDescriptionParser referenceDescriptionParser = new ReferenceDescriptionParser();

    @Override
    public T parseProperty(T description, JsonNode property) {
        ObjectProperty<ReferenceDescription> reference;
        if (property.isObject()) {
            ReferenceDescription referenceDescription = referenceDescriptionParser.parse((ObjectNode) property);
            reference = new ObjectProperty<>(property, referenceDescription);
        } else if (property.isTextual() && isUrl(property.textValue())) {
            reference = new ObjectProperty<>(property, property.textValue());
        } else {
            reference = null;
        }
        description.setReference(reference);
        return description;
    }

    private boolean isUrl(String textValue) {
        // TODO
        return true;
    }
}
