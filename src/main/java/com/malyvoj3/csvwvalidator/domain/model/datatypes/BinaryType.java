package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import java.util.Base64;

public class BinaryType extends StringType {

    private byte[] byteArray;

    public BinaryType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        try {
            byteArray = Base64.getDecoder().decode(stringValue);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    public BinaryType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        try {
            byteArray = Base64.getDecoder().decode(stringValue);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int getLength() {
        return byteArray.length;
    }
}
