package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArrayProperty<E> {

    private final String stringValue;
    private final List<E> parsedValue;
    private List<E> normalizedValue;

}
