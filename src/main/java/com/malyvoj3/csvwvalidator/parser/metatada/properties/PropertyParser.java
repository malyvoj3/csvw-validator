package com.malyvoj3.csvwvalidator.parser.metatada.properties;

import com.fasterxml.jackson.databind.JsonNode;

public interface PropertyParser<T> {

    T parseProperty(T description, JsonNode property);

}
