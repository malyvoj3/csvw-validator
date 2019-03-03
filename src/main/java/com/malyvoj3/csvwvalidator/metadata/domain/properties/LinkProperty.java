package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LinkProperty {

    private final JsonNode jsonValue;
    private final String parsedValue;
    private String normalizedValue;
}
