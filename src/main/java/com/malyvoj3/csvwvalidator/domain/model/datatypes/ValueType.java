package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public abstract class ValueType {

    protected String stringValue;

    public ValueType(String stringValue) {
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

    public int compareTo(ValueType other) throws IncomparableDataTypeException {
        throw new UnsupportedOperationException();
    }

    public Long getLength() {
        throw new UnsupportedOperationException();
    }

    public boolean isGreater(ValueType other) throws IncomparableDataTypeException {
        return compareTo(other) > 0;
    }

    public boolean isGreaterEq(ValueType other) throws IncomparableDataTypeException {
        return compareTo(other) >= 0;
    }

    public boolean isLower(ValueType other) throws IncomparableDataTypeException {
        return compareTo(other) < 0;
    }

    public boolean isLowerEq(ValueType other) throws IncomparableDataTypeException {
        return compareTo(other) <= 0;
    }

    public boolean isEquals(ValueType other) throws IncomparableDataTypeException {
        return compareTo(other) == 0;
    }

}
