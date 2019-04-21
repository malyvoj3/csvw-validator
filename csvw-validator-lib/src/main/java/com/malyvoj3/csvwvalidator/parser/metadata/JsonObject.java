package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonObject extends AbstractJsonProperty<ObjectNode> {

    public JsonObject(String name, ObjectNode jsonValue) {
        super(name, jsonValue);
    }
}
