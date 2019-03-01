package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;

public class BooleanAtomicProperty extends AtomicProperty<Boolean> {

    public BooleanAtomicProperty(JsonNode jsonValue, Boolean parsedValue) {
        super(jsonValue, parsedValue);
    }
}
