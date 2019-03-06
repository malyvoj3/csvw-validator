package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ListAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NullPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    private static final String NULL_VALUE_DEFAULT_VALUE = "";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
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
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
            nullValues = Collections.singletonList(NULL_VALUE_DEFAULT_VALUE);
        }
        ListAtomicProperty<String> nullValue = new ListAtomicProperty<>(nullValues);
        description.setNullValue(nullValue);
    }
}
