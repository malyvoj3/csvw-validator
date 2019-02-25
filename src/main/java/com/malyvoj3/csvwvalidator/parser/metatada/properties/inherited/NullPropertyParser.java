package com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ListAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NullPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String NULL_VALUE_DEFAULT_VALUE = "";

    @Override
    public T parseProperty(T description, JsonNode property) {
        List<String> nullValues;
        if (property.isTextual()) {
            nullValues = Collections.singletonList(property.textValue());
        } else if (property.isArray()) {
            nullValues = new ArrayList<>();
            ArrayNode arrayNode = (ArrayNode) property;
            // TODO: Maybe deduplicate this array using the set?
            arrayNode.elements().forEachRemaining(jsonNode -> {
                if (jsonNode.isTextual()) {
                    nullValues.add(jsonNode.textValue());
                }
            });
        } else {
            nullValues = Collections.singletonList(NULL_VALUE_DEFAULT_VALUE);
        }
        ListAtomicProperty<String> nullValue = new ListAtomicProperty<>(property, nullValues);
        description.setNullValue(nullValue);
        return description;
    }
}
