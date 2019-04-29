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

    public JsonParserError(Severity severity, String messageCode) {
        super(severity, messageCode);
    }

    public JsonParserError(Severity severity, String messageCode, Object[] params) {
        super(severity, messageCode, params);
    }

    public void addKey(String key) {
        jsonKeys.add(key);
    }

    @Override
    public ValidationError getFormattedMessage() {
        return new ValidationError(getSeverity(), getMessageCode(), new Object[]{createPropertyPath(jsonKeys), getParams()});
    }

    public static JsonParserError invalidType(String jsonKey, String correctType) {
        JsonParserError jsonParserError = new JsonParserError(Severity.FATAL, "error.invalidType", new Object[]{correctType});
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError isBlankNode(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.FATAL, "error.blankNode");
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError invalidPropertyType(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.WARNING, "error.invalidPropertyType");
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError unknownProperty(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.WARNING, "error.unknownProperty");
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError forbiddenProperty(String jsonKey) {
        JsonParserError jsonParserError = new JsonParserError(Severity.FATAL, "error.forbiddenProperty");
        jsonParserError.addKey(jsonKey);
        return jsonParserError;
    }

    public static JsonParserError fatal(String msg) {
        return new JsonParserError(Severity.FATAL, msg);
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
