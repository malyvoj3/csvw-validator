package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;

public class IntegerAtomicProperty extends AtomicProperty<Integer> {

    public IntegerAtomicProperty(JsonNode jsonValue, Integer parsedValue) {
        super(jsonValue, parsedValue);
    }
}
