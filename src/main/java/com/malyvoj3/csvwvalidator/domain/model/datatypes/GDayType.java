package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GDayType extends GDateType {

    public GDayType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
    }

    public GDayType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
    }

    @Override
    public int compareTo(DataType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        GDayType that = (GDayType) other;
        return value.compare(that.getValue());
    }

}
