package com.malyvoj3.csvwvalidator.domain.model.datatypes;

public class NMTokenType extends NormalizedStringType {

    private static final String NM_TOKEN_TYPE_PATTERN = "/^[a-zA-Z0-9._-:]*$/";

    public NMTokenType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, NM_TOKEN_TYPE_PATTERN);
    }

    public NMTokenType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        matchPattern(stringValue, NM_TOKEN_TYPE_PATTERN);
    }
}
