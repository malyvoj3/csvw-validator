package com.malyvoj3.csvwvalidator.domain.model.datatypes;

public class DateType extends DataType {

    public DateType(String stringValue) {
        super(stringValue);
    }

    @Override
    public boolean isLengthDataType() {
        return false;
    }

    @Override
    public boolean isValueDataType() {
        return true;
    }
}
