package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;

public class DoubleAtomicProperty extends AtomicProperty<Double> {

    public DoubleAtomicProperty(JsonNode jsonValue, Double parsedValue) {
        super(jsonValue, parsedValue);
    }
}
