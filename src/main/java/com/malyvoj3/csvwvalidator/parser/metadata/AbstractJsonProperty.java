package com.malyvoj3.csvwvalidator.parser.metadata;

import com.malyvoj3.csvwvalidator.validation.JsonParserError;
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
    private ParsingContext parsingContext;
    private List<JsonParserError> parsingErrors = new ArrayList<>();

    public void addError(JsonParserError error) {
        parsingErrors.add(error);
    }

    public void addKeyToErrors(String jsonKey) {
        for (JsonParserError error : parsingErrors) {
            error.addKey(jsonKey);
        }
    }

}
