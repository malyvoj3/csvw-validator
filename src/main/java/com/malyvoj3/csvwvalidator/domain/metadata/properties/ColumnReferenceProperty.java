package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ColumnReferenceProperty {

    private final String stringValue;
    private final List<String> parsedValue;
    private List<String> normalizedValue;

}
