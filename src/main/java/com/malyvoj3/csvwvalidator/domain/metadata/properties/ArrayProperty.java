package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArrayProperty<E> {

    private final JsonNode jsonValue;
    private final List<E> parsedValue;
    private List<E> normalizedValue;

}
