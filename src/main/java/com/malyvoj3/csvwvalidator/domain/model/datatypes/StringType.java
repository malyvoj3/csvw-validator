package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StringType extends DataType {

    public String format;

    public StringType(String stringValue) {
        super(stringValue);
    }

    public StringType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue);
        this.format = format;
        matchPattern(stringValue, format);
    }

    @Override
    public boolean isLengthDataType() {
        return true;
    }

    @Override
    public boolean isValueDataType() {
        return false;
    }

    @Override
    public int getLength() {
        return stringValue.length();
    }
}
