package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;

/**
 * Parser of one description object.
 * @param <T>
 */
public interface ObjectDescriptionParser<T extends ObjectDescription> {

    /**
     * Parse given json object as some description object.
     * @param jsonObject Object to parse.
     * @return Parsed description object.
     */
    T parse(JsonObject jsonObject);

}
