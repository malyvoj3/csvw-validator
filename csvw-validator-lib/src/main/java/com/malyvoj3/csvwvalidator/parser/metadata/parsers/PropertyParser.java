package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;

/**
 * Parser for one property of some object.
 * @param <T>
 */
public interface PropertyParser<T extends ObjectDescription> {

    /**
     * Parse the given property and validates it.
     * @param description Description object in which is the property.
     * @param jsonProperty The JSON property object, which should be parsed.
     */
    void parsePropertyToDescription(T description, JsonProperty jsonProperty);

}
