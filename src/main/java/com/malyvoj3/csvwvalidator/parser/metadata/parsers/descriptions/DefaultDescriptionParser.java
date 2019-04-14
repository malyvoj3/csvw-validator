package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.ObjectDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class DefaultDescriptionParser<T extends ObjectDescription> implements ObjectDescriptionParser<T> {

    private final ParserFactory<T> factory;

    @Override
    public T parse(@NonNull JsonObject jsonObject) {
        T description = createNewDescription();
        jsonObject.getJsonValue().fields().forEachRemaining(entry -> {
            PropertyParser<T> propertyParser = factory.createParser(entry.getKey());
            if (propertyParser != null) {
                JsonProperty jsonProperty = new JsonProperty(entry.getKey(), entry.getValue());
                propertyParser.parsePropertyToDescription(description, jsonProperty);
                jsonProperty.getParsingErrors().forEach(error -> {
                    if (jsonObject.getName() != null) {
                        error.addKey(jsonObject.getName());
                    }
                    jsonObject.addError(error);
                });
            } else {
                log.warn("Unknown property {}.", entry.getKey());
                JsonParserError error = JsonParserError.unknownProperty(entry.getKey());
                if (jsonObject.getName() != null) {
                    error.addKey(jsonObject.getName());
                }
                jsonObject.addError(error);
            }
        });
        return description;
    }

    protected abstract T createNewDescription();
}
