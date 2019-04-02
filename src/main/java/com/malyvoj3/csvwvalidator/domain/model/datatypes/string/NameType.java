package com.malyvoj3.csvwvalidator.domain.model.datatypes.string;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;

public class NameType extends NormalizedStringType {

    private static final String NAME_TYPE_PATTERN = "/[a-zA-Z_:][\\w.-:]*$/";

    public NameType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, NAME_TYPE_PATTERN);
    }

    public NameType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        matchPattern(stringValue, NAME_TYPE_PATTERN);
    }

}
