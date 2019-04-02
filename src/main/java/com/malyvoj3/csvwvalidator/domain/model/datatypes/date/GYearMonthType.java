package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GYearMonthType extends GDateType {

    public GYearMonthType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
    }

    public GYearMonthType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
    }

    @Override
    public int compareTo(DataType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        GYearMonthType that = (GYearMonthType) other;
        return value.compare(that.getValue());
    }

}
