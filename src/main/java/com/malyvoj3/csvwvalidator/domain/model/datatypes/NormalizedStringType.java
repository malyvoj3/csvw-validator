package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import org.apache.commons.lang3.StringUtils;

public class NormalizedStringType extends StringType {

    public NormalizedStringType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        if (containsForbiddenWhitespace(stringValue)) {
            throw new DataTypeFormatException();
        }
    }

    public NormalizedStringType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        if (containsForbiddenWhitespace(stringValue)) {
            throw new DataTypeFormatException();
        }
    }

    private boolean containsForbiddenWhitespace(String string) {
        return StringUtils.containsAny(string, '\r', '\n', '\t');
    }
}
