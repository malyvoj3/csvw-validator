package com.malyvoj3.csvwvalidator.parser.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;

/**
 * Factory which creates parsers for properties.
 * @param <T>
 */
public interface ParserFactory<T extends ObjectDescription> {

    /**
     * Create parser factory for property given by name.
     * @param propertyName Name of the property which should be parsed.
     * @return PropertyParser for given propertyName, or null if this property is not allowed by CSV on the Web.
     */
    PropertyParser<T> createParser(String propertyName);

}
