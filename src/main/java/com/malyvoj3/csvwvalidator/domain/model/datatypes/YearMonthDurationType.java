package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;

import javax.xml.datatype.DatatypeFactory;

@Data
public class YearMonthDurationType extends DurationType {

    public YearMonthDurationType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        try {
            value = DatatypeFactory.newInstance().newDurationDayTime(stringValue);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    public YearMonthDurationType(String stringValue, String format) throws DataTypeFormatException {
        this(stringValue);
        matchPattern(stringValue, format);
        this.format = format;
    }

    @Override
    public int compareTo(DataType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        YearMonthDurationType that = (YearMonthDurationType) other;
        return value.compare(that.getValue());
    }

}
