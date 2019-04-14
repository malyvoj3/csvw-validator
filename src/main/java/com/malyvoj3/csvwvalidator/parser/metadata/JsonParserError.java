package com.malyvoj3.csvwvalidator.parser.metadata;

import com.malyvoj3.csvwvalidator.domain.Severity;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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

    @Override
    public String getFormattedMessage() {
        return new StringBuilder(getMessage())
                .append(" '")
                .append(createPropertyPath(jsonKeys))
                .append("'.")
                .toString();
    }

    public static JsonParserError invalidType(String jsonKey, String correctType) {
        JsonParserError jsonParserError = new JsonParserError(Severity.FATAL, String.format("Invalid type. It should be %s.", correctType));
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError isBlankNode(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.FATAL, "Property is blank node");
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

    private String createPropertyPath(@NonNull List<String> jsonKeys) {
        int length = jsonKeys.size();
        if (length == 1) {
            return jsonKeys.get(0);
        }
        StringBuilder stringBuilder = new StringBuilder(jsonKeys.get(length - 1));
        for (int i = length - 2; i >= 0; i--) {
            stringBuilder.append(".");
            stringBuilder.append(jsonKeys.get(i));
        }
        return stringBuilder.toString();
    }
}
