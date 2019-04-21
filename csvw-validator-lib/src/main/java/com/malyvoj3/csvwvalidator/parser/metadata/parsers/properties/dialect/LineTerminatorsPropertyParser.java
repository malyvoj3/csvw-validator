package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ListAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineTerminatorsPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final List<String> lineTerminatorsDefaultValue =
            Stream.of("\\r\\n", "\\n").collect(Collectors.toList());

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        boolean isCorrectProperty = false;
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
                isCorrectProperty = true;
            }
        }
        if (!isCorrectProperty) {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setLineTerminators(lineTerminators);
    }

}
