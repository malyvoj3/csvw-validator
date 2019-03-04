package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ListAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineTerminatorsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final List<String> lineTerminatorsDefaultValue =
            Stream.of("\\r\\n", "\\n").collect(Collectors.toList());

    @Override
    public T parseProperty(T description, JsonNode property) {
        ListAtomicProperty<String> lineTerminators = new ListAtomicProperty<>(lineTerminatorsDefaultValue);
        if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            if (arrayNode.size() > 0) {
                List<String> list = new ArrayList<>();
                arrayNode.elements().forEachRemaining(jsonNode -> {
                    if (jsonNode.isTextual()) {
                        list.add(jsonNode.textValue());
                    }
                });
                lineTerminators = new ListAtomicProperty<>(list);
            }
        }
        description.setLineTerminators(lineTerminators);
        return description;
    }
}
