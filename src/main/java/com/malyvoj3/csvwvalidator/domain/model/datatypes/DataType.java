package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public abstract class DataType {

    protected String stringValue;

    public DataType(String stringValue) {
        this.stringValue = stringValue;
    }

    protected void matchPattern(String string, String format) throws DataTypeFormatException {
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            throw new DataTypeFormatException();
        }
    }

    public abstract boolean isLengthDataType();
    public abstract boolean isValueDataType();

    public String getCanonicalForm() {
        return stringValue;
    }

    public int compareTo(DataType other) throws IncomparableDataTypeException {
        throw new UnsupportedOperationException();
    }

    public int getLength() {
        throw new UnsupportedOperationException();
    }

    public boolean isGreater(DataType other) throws IncomparableDataTypeException {
        return compareTo(other) > 0;
    }

    public boolean isGreaterEq(DataType other) throws IncomparableDataTypeException {
        return compareTo(other) >= 0;
    }

    public boolean isLower(DataType other) throws IncomparableDataTypeException {
        return compareTo(other) < 0;
    }

    public boolean isLowerEq(DataType other) throws IncomparableDataTypeException {
        return compareTo(other) <= 0;
    }

    public boolean isEquals(DataType other) throws IncomparableDataTypeException {
        return compareTo(other) == 0;
    }

}
