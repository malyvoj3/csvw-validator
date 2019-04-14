package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.LanguageUtils;
import lombok.NonNull;

import java.util.*;

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
            JsonProperty tmp = new JsonProperty(jsonProperty.getName(), arrayNode);
            titlesMap.put(CsvwKeywords.NATURAL_LANGUAGE_CODE, titlesFromArray(arrayNode, tmp));
            tmp.getParsingErrors().forEach(error -> {
                error.addKey(jsonProperty.getName());
                jsonProperty.addError(error);
            });
        } else if (property.isObject()) {
            ObjectNode objectNode = (ObjectNode) property;
            JsonProperty tmp = new JsonProperty(jsonProperty.getName(), objectNode);
            titlesMap = titlesFromObject(objectNode, jsonProperty);
            tmp.getParsingErrors().forEach(error -> {
                error.addKey(jsonProperty.getName());
                jsonProperty.addError(error);
            });
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }

        if (!titlesMap.isEmpty()) {
            titles = new NaturalLanguageProperty(titlesMap);
        }
        description.setTitles(titles);
    }

    private Map<String, List<String>> titlesFromObject(ObjectNode objectNode, JsonProperty jsonProperty) {
        Map<String, List<String>> map = new HashMap<>();
        objectNode.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            if (LanguageUtils.isLanguageTag(key)) {
                JsonNode value = entry.getValue();
                if (value.isTextual()) {
                    putToMap(key, titlesFromValue(value.textValue()), map);
                } else if (value.isArray()) {
                    JsonProperty tmp = new JsonProperty(key, null);
                    putToMap(key, titlesFromArray((ArrayNode) value, tmp), map);
                    tmp.getParsingErrors().forEach(error -> {
                        error.addKey(key);
                        jsonProperty.addError(error);
                    });
                }
            }
        });
        return map;
    }

    private List<String> titlesFromArray(ArrayNode arrayNode, JsonProperty jsonProperty) {
        List<String> titlesList = new ArrayList<>();
        int arrayCounter = 0;
        for (Iterator<JsonNode> iter = arrayNode.elements(); iter.hasNext(); arrayCounter++) {
            JsonNode jsonNode = iter.next();
            if (jsonNode.isTextual()) {
                titlesList.add(jsonNode.textValue());
            } else {
                jsonProperty.addError(JsonParserError.invalidPropertyType(String.valueOf(arrayCounter)));
            }
        }
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
