package com.malyvoj3.csvwvalidator.validation;

public class ErrorFactory {

    public static JsonParserError jsonParserError(Severity severity, String message, String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(severity, message);
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError invalidPropertyType(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.WARNING, "Invalid property type");
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError unknownProperty(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.WARNING, "Unknown property");
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

}
