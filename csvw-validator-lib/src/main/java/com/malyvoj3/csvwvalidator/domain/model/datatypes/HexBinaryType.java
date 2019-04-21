package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.string.StringType;

public class HexBinaryType extends StringType {

    private static final String HEX_BINARY_PATTERN = "([0-9a-fA-F]{2})*";

    public HexBinaryType(String stringValue) throws DataTypeFormatException {
        super(stringValue, HEX_BINARY_PATTERN);
        this.format = null;
    }

    public HexBinaryType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, HEX_BINARY_PATTERN);
        this.format = format;
        matchPattern(stringValue, format);
    }

    @Override
    public Long getLength() {
        long length;
        if (stringValue != null) {
            length = (long) (stringValue.length() / 2);
        } else {
            length = 0L;
        }
        return length;
    }
}
