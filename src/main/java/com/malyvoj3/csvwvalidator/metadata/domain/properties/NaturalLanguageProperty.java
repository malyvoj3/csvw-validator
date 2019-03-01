package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class NaturalLanguageProperty {
    private final Map<String, List<String>> values;
}
