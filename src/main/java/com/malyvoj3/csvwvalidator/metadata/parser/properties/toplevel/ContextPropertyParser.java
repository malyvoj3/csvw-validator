package com.malyvoj3.csvwvalidator.metadata.parser.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.Context;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class ContextPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {
    @Override
    public T parseProperty(T description, JsonNode property) {
        Context context = null;
        if (property.isTextual()) {
            context = new Context();
            // Then string must be URL of CSVW Vocabulary
            // TODO Normalize URL pred porovnani??
            if (CsvwKeywords.CSVW_VOCABULARY_URL.equals(property.textValue())) {
                context.setRefContext(new StringAtomicProperty(property, CsvwKeywords.CSVW_VOCABULARY_URL));
            }
        } else if (property.isArray()) {
            ArrayNode arrayNode = (ArrayNode) property;
            if (arrayNode.size() == 2) {
                JsonNode first = arrayNode.get(0);
                JsonNode second = arrayNode.get(1);
                if (first.isTextual() && CsvwKeywords.CSVW_VOCABULARY_URL.equals(first.textValue()) && second.isObject()) {
                    context = new Context();
                    ObjectNode objectNode = (ObjectNode) second;
                    JsonNode base = objectNode.get(CsvwKeywords.BASE_PROPERTY);
                    JsonNode language = objectNode.get(CsvwKeywords.LANGUAGE_PROPERTY);
                    addBase(context, base);
                    addLanguage(context, language);
                }
            }
        }
        description.setContext(context);
        return description;
    }

    private void addBase(Context context, JsonNode base) {
        if (base != null && base.isTextual() && isUrl(base.textValue())) {
            context.setBase(new StringAtomicProperty(base, base.textValue()));
        }
    }

    private void addLanguage(Context context, JsonNode language) {
        if (language != null && language.isTextual() && isLanguageCode(language.textValue())) {
            context.setLanguage(new StringAtomicProperty(language, language.textValue()));
        }
    }

    private boolean isLanguageCode(String textValue) {
        // TODO
        return true;
    }

    private boolean isUrl(String textValue) {
        // TODO
        return true;
    }
}
