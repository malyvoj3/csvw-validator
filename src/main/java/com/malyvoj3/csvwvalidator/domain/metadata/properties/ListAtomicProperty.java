package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ListAtomicProperty<E> extends AtomicProperty<List<E>> {

    public ListAtomicProperty(JsonNode jsonValue, List<E> parsedValue) {
        super(jsonValue, parsedValue);
    }
}
