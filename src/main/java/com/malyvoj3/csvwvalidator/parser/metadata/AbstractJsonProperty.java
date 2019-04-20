package com.malyvoj3.csvwvalidator.parser.metadata;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractJsonProperty<T> {

    public AbstractJsonProperty(String name, T jsonValue) {
        this.name = name;
        this.jsonValue = jsonValue;
    }

    private String name;
    private T jsonValue;
    private List<JsonParserError> errors = new ArrayList<>();

    public void addError(JsonParserError error) {
        errors.add(error);
    }

}
