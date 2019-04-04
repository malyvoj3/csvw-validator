package com.malyvoj3.csvwvalidator.domain.model.datatypes.duration;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Data;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

@Data
public class DurationType extends ValueType {

    protected Duration value;
    protected String format;

    public DurationType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        try {
            value = DatatypeFactory.newInstance().newDuration(stringValue);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    public DurationType(String stringValue, String format) throws DataTypeFormatException {
        this(stringValue);
        matchPattern(stringValue, format);
    }

    @Override
    public String getCanonicalForm() {
        return value.toString();
    }

    @Override
    public int compareTo(ValueType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DurationType that = (DurationType) other;
        return value.compare(that.getValue());
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
