package com.malyvoj3.csvwvalidator.domain.model.datatypes.string;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import org.apache.commons.lang3.StringUtils;

public class TokenType extends NormalizedStringType {

    public TokenType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        if (isForbiddenString(stringValue)) {
            throw new DataTypeFormatException();
        }
    }

    public TokenType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        if (isForbiddenString(stringValue)) {
            throw new DataTypeFormatException();
        }
    }

    private boolean isForbiddenString(String string) {
        return StringUtils.isNotEmpty(string) && (Character.isWhitespace(string.charAt(0))
                || Character.isWhitespace(string.charAt(string.length() - 1))
                || containsMultipleSpaces(string));
    }

    private boolean containsMultipleSpaces(String value) {
        for (int i = 0; i < value.length() - 1; i++) {
            if (Character.isWhitespace(value.charAt(i)) && Character.isWhitespace(value.charAt(i + 1))) {
                return true;
            }
        }
        return false;
    }
}
