package com.malyvoj3.csvwvalidator.validation;

import lombok.NonNull;

import java.util.List;

public class JsonParserErrorDefaultFormatter implements ValidationErrorFormatter<JsonParserError> {

    @Override
    public String format(JsonParserError error) {
        StringBuilder stringBuilder = new StringBuilder(error.getSeverity().name());
        stringBuilder.append(": ");
        stringBuilder.append(error.getMessage());
        stringBuilder.append(" '");
        stringBuilder.append(createPropertyPath(error.getJsonKeys()));
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
