package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;

public class DateTimeStampType extends DateTimeType {

    private static final String DATE_TIME_STAMP_PATTERN = ".*(Z|(\\+|-)[0-9][0-9]:?([0-9][0-9])?)";

    public DateTimeStampType(String stringValue, String dateTimePattern) throws DataTypeFormatException {
        super(stringValue, dateTimePattern);
        matchPattern(stringValue, DATE_TIME_STAMP_PATTERN);
    }

}
