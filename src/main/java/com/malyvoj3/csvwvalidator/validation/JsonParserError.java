package com.malyvoj3.csvwvalidator.validation;

import java.util.ArrayList;
import java.util.List;

public class JsonParserError extends ValidationError {

    List<String> jsonKeys = new ArrayList<>();

    public JsonParserError(Severity severity, String message) {
        super(severity, message);
    }

    public void addKey(String key) {
        jsonKeys.add(key);
    }

}
