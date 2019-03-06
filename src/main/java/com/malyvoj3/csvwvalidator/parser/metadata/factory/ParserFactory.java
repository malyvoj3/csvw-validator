package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;

public interface ParserFactory<T> {

    PropertyParser<T> createParser(String propertyName);

}
