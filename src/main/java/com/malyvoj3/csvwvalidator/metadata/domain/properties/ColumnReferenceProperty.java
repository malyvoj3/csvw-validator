package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ColumnReferenceProperty {

    private final JsonNode jsonValue;
    private final List<String> parsedValue;
    private List<String> normalizedValue;

}
