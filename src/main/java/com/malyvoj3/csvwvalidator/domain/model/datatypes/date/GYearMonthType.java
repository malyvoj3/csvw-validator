package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GYearMonthType extends GDateType {

    private static final String G_YEAR_MONTH_PATTERN = "-?([1-9][0-9]{3,}|0[0-9]{3})-(0[1-9]|1[0-2])(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?";

    public GYearMonthType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, G_YEAR_MONTH_PATTERN);
    }

    public GYearMonthType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        matchPattern(stringValue, G_YEAR_MONTH_PATTERN);
    }

    @Override
    public int compareTo(ValueType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        GYearMonthType that = (GYearMonthType) other;
        return value.compare(that.getValue());
    }

}
