package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GMonthDayType extends GDateType {

    private static final String G_MONTH_DAY_PATTERN = "--(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?";

    public GMonthDayType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, G_MONTH_DAY_PATTERN);
    }

    public GMonthDayType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        matchPattern(stringValue, G_MONTH_DAY_PATTERN);
    }

    @Override
    public int compareTo(DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        GMonthDayType that = (GMonthDayType) other;
        return value.compare(that.getValue());
    }

}
