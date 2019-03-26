package com.malyvoj3.csvwvalidator.validation;

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
    public String format() {
        StringBuilder stringBuilder = new StringBuilder(getSeverity().name());
        stringBuilder.append(": ");
        stringBuilder.append(getMessage());
        stringBuilder.append(" '");
        stringBuilder.append(createPropertyPath(jsonKeys));
        stringBuilder.append("'.");
        return stringBuilder.toString();
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
