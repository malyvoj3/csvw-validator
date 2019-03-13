package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.LanguageUtils;
import com.malyvoj3.csvwvalidator.validation.ErrorFactory;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitlesPropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        NaturalLanguageProperty titles = null;
        Map<String, List<String>> titlesMap = new HashMap<>();
        if (property.isTextual()) {
            titlesMap.put(CsvwKeywords.NATURAL_LANGUAGE_CODE, titlesFromValue(property.textValue()));
        } else if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            titlesMap.put(CsvwKeywords.NATURAL_LANGUAGE_CODE, titlesFromArray(arrayNode));
        } else if (property.isObject()) {
            ObjectNode objectNode = (ObjectNode) property;
            titlesMap = titlesFromObject(objectNode);
        } else {
            jsonProperty.addError(ErrorFactory.invalidPropertyType(jsonProperty.getName()));
        }

        if (!titlesMap.isEmpty()) {
            titles = new NaturalLanguageProperty(titlesMap);
        }
        description.setTitles(titles);
    }

    private Map<String, List<String>> titlesFromObject(ObjectNode objectNode) {
        Map<String, List<String>> map = new HashMap<>();
        objectNode.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            if (LanguageUtils.isLanguageTag(key)) {
                JsonNode value = entry.getValue();
                if (value.isTextual()) {
                    putToMap(key, titlesFromValue(value.textValue()), map);
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

    private List<String> titlesFromValue(String value) {
        List<String> titlesList = new ArrayList<>();
        titlesList.add(value);
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

}
