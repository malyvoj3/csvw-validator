package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnsignedLongType extends IntegerType {

    private static final String MAX_UNSIGNED_LONG = "18446744073709551615";
    private static final String MIN_UNSIGNED_LONG = "0";

    public UnsignedLongType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        if (getValue().compareTo(new BigDecimal(MIN_UNSIGNED_LONG)) < 0 && getValue().compareTo(new BigDecimal(MAX_UNSIGNED_LONG)) > 0) {
            throw new DataTypeFormatException();
        }
    }

    public UnsignedLongType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue, format);
        if (getValue().compareTo(new BigDecimal(MIN_UNSIGNED_LONG)) < 0 && getValue().compareTo(new BigDecimal(MAX_UNSIGNED_LONG)) > 0) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        PositiveIntegerType that = (PositiveIntegerType) other;
        return getValue().compareTo(that.getValue());
    }
}
