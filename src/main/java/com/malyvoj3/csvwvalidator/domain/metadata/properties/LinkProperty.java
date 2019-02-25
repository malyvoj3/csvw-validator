package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinkProperty {

    private final JsonNode jsonValue;
    private final String parsedValue;
    private String normalizedValue;
}
