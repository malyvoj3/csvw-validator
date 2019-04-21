package com.malyvoj3.csvwvalidator.parser.metadata.parsers;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;

public interface ObjectDescriptionParser<T extends ObjectDescription> {

    T parse(JsonObject jsonObject);

}
