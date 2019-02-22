package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ObjectProperty<T> {

    private final String stringValue;
    private final T objectValue;
    private T normalizedValue;
}
