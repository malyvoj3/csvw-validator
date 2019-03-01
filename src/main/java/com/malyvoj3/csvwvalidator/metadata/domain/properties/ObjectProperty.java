package com.malyvoj3.csvwvalidator.metadata.domain.properties;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectProperty<T> {

    private final JsonNode jsonValue;
    private String objectUrl;
    private T objectValue;
    private T normalizedValue;

    public ObjectProperty(JsonNode jsonValue, String objectUrl) {
        this.jsonValue = jsonValue;
        this.objectUrl = objectUrl;
    }

    public ObjectProperty(JsonNode jsonValue, T objectValue) {
        this.jsonValue = jsonValue;
        this.objectValue = objectValue;
    }
}
