package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinkProperty {

    private final JsonNode jsonValue;
    private final String parsedValue;
    private String normalizedValue;
}
