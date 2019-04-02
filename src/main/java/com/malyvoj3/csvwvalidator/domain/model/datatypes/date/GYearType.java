package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GYearType extends GDateType {

    public GYearType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
    }

    public GYearType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
    }

    @Override
    public int compareTo(DataType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        GYearType that = (GYearType) other;
        return value.compare(that.getValue());
    }

}
