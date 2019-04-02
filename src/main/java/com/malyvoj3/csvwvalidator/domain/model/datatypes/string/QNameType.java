package com.malyvoj3.csvwvalidator.domain.model.datatypes.string;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;

public class QNameType extends StringType {

    private static final String NC_NAME_PATTERN = ".*:^[a-zA-Z_][\\w.\\-]*";

    public QNameType(String stringValue) throws DataTypeFormatException {
        super(stringValue, NC_NAME_PATTERN);
        this.format = null;
    }

    public QNameType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        this.format = format;
        matchPattern(stringValue, format);
    }
}
