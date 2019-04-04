package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public abstract class DataTypeDefinition {

    protected String stringValue;

    public DataTypeDefinition(String stringValue) {
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

    public int compareTo(DataTypeDefinition other) throws IncomparableDataTypeException {
        throw new UnsupportedOperationException();
    }

    public Long getLength() {
        throw new UnsupportedOperationException();
    }

    public boolean isGreater(DataTypeDefinition other) throws IncomparableDataTypeException {
        return compareTo(other) > 0;
    }

    public boolean isGreaterEq(DataTypeDefinition other) throws IncomparableDataTypeException {
        return compareTo(other) >= 0;
    }

    public boolean isLower(DataTypeDefinition other) throws IncomparableDataTypeException {
        return compareTo(other) < 0;
    }

    public boolean isLowerEq(DataTypeDefinition other) throws IncomparableDataTypeException {
        return compareTo(other) <= 0;
    }

    public boolean isEquals(DataTypeDefinition other) throws IncomparableDataTypeException {
        return compareTo(other) == 0;
    }

}
