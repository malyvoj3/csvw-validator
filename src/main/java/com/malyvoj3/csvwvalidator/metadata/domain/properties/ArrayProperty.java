package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ArrayProperty<E> {

    private final JsonNode jsonValue;
    private final List<E> parsedValue;
    private List<E> normalizedValue;

}
