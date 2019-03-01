package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjectProperty<T> {

    private final JsonNode jsonValue;
    private final T objectValue;
    private T normalizedValue;
}
