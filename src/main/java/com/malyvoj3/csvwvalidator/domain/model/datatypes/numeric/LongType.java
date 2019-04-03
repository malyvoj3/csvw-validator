package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class LongType extends NumericType {

    private static final String LONG_PATTERN = "[\\-+]?[0-9]+";

    private Long value;

    public LongType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, LONG_PATTERN);
        try {
            this.value = parseNumber(stringValue, null).longValueExact();
        } catch (AssertionError ex) {
            throw new DataTypeFormatException();
        }
    }

    public LongType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, LONG_PATTERN);
        try {
            this.value = parseNumber(stringValue, format).longValueExact();
        } catch (AssertionError ex) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        LongType that = (LongType) other;
        return value.compareTo(that.getValue());
    }
}
