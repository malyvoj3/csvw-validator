package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import org.apache.commons.lang3.StringUtils;

public class BooleanType extends DataTypeDefinition {

    private static final String TRUE_STRING = "true";
    private static final String FALSE_STRING = "false";
    private static final String ONE_STRING = "1";
    private static final String ZERO_STRING = "0";

    private boolean value;
    private String format;
    private String trueValue;
    private String falseValue;

    public BooleanType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        if (TRUE_STRING.equals(stringValue) || ONE_STRING.equals(stringValue)) {
            value = true;
        } else if (FALSE_STRING.equals(stringValue) || ZERO_STRING.equals(stringValue)) {
            value = false;
        } else {
            throw new DataTypeFormatException();
        }
    }

    public BooleanType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue);
        String[] formatValues = StringUtils.split(format, '|');
        if (formatValues != null && formatValues.length == 2) {
            trueValue = formatValues[0];
            falseValue = formatValues[1];
        } else {
            throw new DataTypeFormatException();
        }
        this.format = format;
        if (trueValue.equals(stringValue)) {
            value = true;
        } else if (falseValue.equals(stringValue)) {
            value = false;
        } else {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public String getCanonicalForm() {
        return String.valueOf(value);
    }

    @Override
    public boolean isLengthDataType() {
        return false;
    }

    @Override
    public boolean isValueDataType() {
        return false;
    }
}
