package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UriTemplateProperty {

    // TODO: mozna nechat jen jednu z final hodnot
    private final JsonNode jsonValue;
    private final String parsedValue;
    private String normalizedValue;

}
