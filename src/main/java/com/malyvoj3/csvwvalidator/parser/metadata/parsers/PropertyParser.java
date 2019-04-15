package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;

public interface PropertyParser<T extends ObjectDescription> {

    void parsePropertyToDescription(T description, JsonProperty jsonProperty);

}
