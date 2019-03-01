package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;

public class StringAtomicProperty extends AtomicProperty<String> {

    public StringAtomicProperty(JsonNode jsonValue, String parsedValue) {
        super(jsonValue, parsedValue);
    }
}
