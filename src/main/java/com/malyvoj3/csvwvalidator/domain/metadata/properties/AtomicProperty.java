package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AtomicProperty<T> {

    private final String stringValue;
    private final T parsedValue;
    private T normalizedValue;

}
