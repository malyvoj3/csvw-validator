package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GMonthType extends GDateType {

    private static final String G_MONTH_PATTERN = "--(0[1-9]|1[0-2])(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?";

    public GMonthType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, G_MONTH_PATTERN);
    }

    public GMonthType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        matchPattern(stringValue, G_MONTH_PATTERN);
    }

    @Override
    public int compareTo(ValueType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        GMonthType that = (GMonthType) other;
        return value.compare(that.getValue());
    }

}
