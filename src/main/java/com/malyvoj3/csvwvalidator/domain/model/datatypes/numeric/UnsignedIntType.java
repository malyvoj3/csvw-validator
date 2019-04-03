package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.NonNull;

public class UnsignedIntType extends LongType {

    private static final Long MAX_UNSIGNED_INT = 4294967295L;
    private static final Long MIN_UNSIGNED_INT = 0L;

    public UnsignedIntType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct();
    }

    public UnsignedIntType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue, format);
        construct();
    }

    private void construct() throws DataTypeFormatException {
        if (getValue() < MIN_UNSIGNED_INT || getValue() > MAX_UNSIGNED_INT) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        UnsignedIntType that = (UnsignedIntType) other;
        return getValue().compareTo(that.getValue());
    }
}
