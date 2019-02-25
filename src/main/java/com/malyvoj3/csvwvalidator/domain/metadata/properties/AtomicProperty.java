package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AtomicProperty<T> {

    private final JsonNode jsonValue;
    private final T parsedValue;
    private T normalizedValue;

}
