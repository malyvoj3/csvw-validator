package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AtomicProperty<T> {

    private final JsonNode jsonValue;
    private final T parsedValue;
    private T normalizedValue;

}
