package com.malyvoj3.csvwvalidator.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class JsonParserError extends ValidationError {

    private List<String> jsonKeys = new ArrayList<>();

    public JsonParserError(Severity severity, String message) {
        super(severity, message);
    }

    public void addKey(String key) {
        jsonKeys.add(key);
    }

}
