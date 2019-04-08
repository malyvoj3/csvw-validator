package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.LanguageUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ContextParser {

    public Context parse(JsonProperty jsonProperty) {
        JsonNode jsonNode = jsonProperty.getJsonValue();
        Context context = null;
        if (jsonNode.isTextual()) {
            context = new Context();
            // Then string must be URL of CSVW Vocabulary
            if (UriUtils.uriEquals(CsvwKeywords.CSVW_VOCABULARY_URL, jsonNode.textValue())) {
                context.setRefContext(new StringAtomicProperty(CsvwKeywords.CSVW_VOCABULARY_URL));
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            if (arrayNode.size() == 2) {
                JsonNode first = arrayNode.get(0);
                JsonNode second = arrayNode.get(1);
                if (first.isTextual() && CsvwKeywords.CSVW_VOCABULARY_URL.equals(first.textValue()) && second.isObject()) {
                    context = new Context();
                    ObjectNode objectNode = (ObjectNode) second;
                    JsonNode base = objectNode.get(CsvwKeywords.BASE_PROPERTY);
                    JsonNode language = objectNode.get(CsvwKeywords.LANGUAGE_PROPERTY);
                    addBase(context, base, jsonProperty);
                    addLanguage(context, language, jsonProperty);
                }
            }
        }
        return context;
    }

    private void addBase(Context context, JsonNode base, JsonProperty jsonProperty) {
        if (base != null) {
            if (base.isTextual()) {
                context.setBase(new StringAtomicProperty(base.textValue()));
            } else {
                jsonProperty.addError(JsonParserError.invalidPropertyType("@base"));
            }
        }
    }

    private void addLanguage(Context context, JsonNode language, JsonProperty jsonProperty) {
        if (language != null) {
            if (language.isTextual() && LanguageUtils.isLanguageTag(language.textValue())) {
                context.setLanguage(new StringAtomicProperty(language.textValue()));
            } else {
                jsonProperty.addError(JsonParserError.invalidPropertyType("@language"));
            }
        }
    }

}
