package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class NegativeIntegerType extends IntegerType {

    public NegativeIntegerType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct();
    }

    public NegativeIntegerType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue, format);
        construct();
    }

    private void construct() throws DataTypeFormatException {
        if (getValue().compareTo(new BigDecimal(0)) >= 0) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull ValueType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        NegativeIntegerType that = (NegativeIntegerType) other;
        return getValue().compareTo(that.getValue());
    }
}
