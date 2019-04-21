package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonProperty extends AbstractJsonProperty<JsonNode> {

    public JsonProperty(String name, JsonNode jsonValue) {
        super(name, jsonValue);
    }
}
