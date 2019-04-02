package com.malyvoj3.csvwvalidator.domain.model.datatypes.duration;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;

import javax.xml.datatype.DatatypeFactory;

@Data
public class DayTimeDurationType extends DurationType {

    public DayTimeDurationType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        try {
            value = DatatypeFactory.newInstance().newDurationDayTime(stringValue);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    public DayTimeDurationType(String stringValue, String format) throws DataTypeFormatException {
        this(stringValue);
        matchPattern(stringValue, format);
        this.format = format;
    }

    @Override
    public int compareTo(DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DayTimeDurationType that = (DayTimeDurationType) other;
        return value.compare(that.getValue());
    }

}
