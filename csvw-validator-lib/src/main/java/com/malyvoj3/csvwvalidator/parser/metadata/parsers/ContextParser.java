package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.LanguageUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
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
                    Context newContext = new Context();
                    ObjectNode objectNode = (ObjectNode) second;
                    objectNode.fields().forEachRemaining(entry -> {
                        if (CsvwKeywords.BASE_PROPERTY.equals(entry.getKey())) {
                            addBase(newContext, entry.getValue(), jsonProperty);
                        } else if (CsvwKeywords.LANGUAGE_PROPERTY.equals(entry.getKey())) {
                            addLanguage(newContext, entry.getValue(), jsonProperty);
                        } else {
                            jsonProperty.addError(JsonParserError.fatal("Property @context has invalid property."));
                        }
                    });
                    context = newContext;
                } else {
                    jsonProperty.addError(JsonParserError.fatal("Property @context is invalid."));
                }
            } else {
                jsonProperty.addError(JsonParserError.fatal("Property @context is invalid."));
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
