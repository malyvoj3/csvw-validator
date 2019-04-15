package com.malyvoj3.csvwvalidator.parser.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;

public interface ParserFactory<T extends ObjectDescription> {

    PropertyParser<T> createParser(String propertyName);

}
