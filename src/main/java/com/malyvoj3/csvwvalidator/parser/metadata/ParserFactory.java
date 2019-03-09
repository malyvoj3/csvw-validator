package com.malyvoj3.csvwvalidator.parser.metadata;

import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;

public interface ParserFactory<T> {

    PropertyParser<T> createParser(String propertyName);

}
