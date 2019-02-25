package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjectProperty<T> {

    private final JsonNode jsonValue;
    private final T objectValue;
    private T normalizedValue;
}
