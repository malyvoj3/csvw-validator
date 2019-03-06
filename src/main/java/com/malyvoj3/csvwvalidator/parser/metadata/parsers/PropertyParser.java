package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;

public interface PropertyParser<T> {

    void parsePropertyToDescription(T description, JsonProperty jsonProperty);

}
