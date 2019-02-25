package com.malyvoj3.csvwvalidator.parser.metatada.properties;

public interface PropertyParser<T> {

    T parseProperty(T description);

}
