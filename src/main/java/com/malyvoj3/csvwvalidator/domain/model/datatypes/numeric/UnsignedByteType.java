package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.NonNull;

public class UnsignedByteType extends LongType {

    private static final Long MAX_UNSIGNED_BYTE = 255L;
    private static final Long MIN_UNSIGNED_BYTE = 0L;

    public UnsignedByteType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct();
    }

    public UnsignedByteType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue, format);
        construct();
    }

    private void construct() throws DataTypeFormatException {
        if (getValue() < MIN_UNSIGNED_BYTE && getValue() > MAX_UNSIGNED_BYTE) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        UnsignedByteType that = (UnsignedByteType) other;
        return getValue().compareTo(that.getValue());
    }
}
