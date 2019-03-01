package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;

public class DoubleAtomicProperty extends AtomicProperty<Double> {

    public DoubleAtomicProperty(JsonNode jsonValue, Double parsedValue) {
        super(jsonValue, parsedValue);
    }
}
