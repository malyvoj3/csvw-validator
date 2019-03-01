package com.malyvoj3.csvwvalidator.metadata.parser.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.ColumnDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

import java.util.*;

public class TitlesPropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description, JsonNode property) {
        Map<String, List<String>> titlesMap = null;
        if (property.isTextual()) {
            titlesMap = Collections.singletonMap(CsvwKeywords.NATURAL_LANGUAGE_CODE,
                    Collections.singletonList(property.textValue()));
        } else if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            titlesMap = Collections.singletonMap(CsvwKeywords.NATURAL_LANGUAGE_CODE, titlesFromArray(arrayNode));
        } else if (property.isObject()) {
            ObjectNode objectNode = (ObjectNode) property;
            titlesMap = titlesFromObject(objectNode);
        }
        NaturalLanguageProperty titles = new NaturalLanguageProperty(titlesMap);
        description.setTitles(titles);
        return description;
    }

    private Map<String, List<String>> titlesFromObject(ObjectNode objectNode) {
        Map<String, List<String>> map = new HashMap<>();
        objectNode.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            if (isLanguageCode(key)) {
                JsonNode value = entry.getValue();
                if (value.isTextual()) {
                    putToMap(key, Collections.singletonList(value.textValue()), map);
                } else if (value.isArray()) {
                    putToMap(key, titlesFromArray((ArrayNode) value), map);
                }
            }
        });
        return map;
    }

    private List<String> titlesFromArray(ArrayNode arrayNode) {
        List<String> titlesList = new ArrayList<>();
        arrayNode.elements().forEachRemaining(jsonNode -> {
            if (jsonNode.isTextual()) {
                titlesList.add(jsonNode.textValue());
            }
        });
        return titlesList;
    }

    /**
     * Put to map - check if there is already key.
     * @param key
     * @param value
     * @param map
     */
    private void putToMap(String key, List<String> value, Map<String, List<String>> map) {
        if (map.containsKey(key)) {
            map.get(key).addAll(value);
        } else {
            map.put(key, value);
        }
    }

    private boolean isLanguageCode(String key) {
        // TODO
        return true;
    }

}
