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
public class IntType extends NumericType {

    private static final String INT_PATTERN = "[\\-+]?[0-9]+";

    private Integer value;

    public IntType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, INT_PATTERN);
        try {
            this.value = parseNumber(stringValue, null).intValueExact();
        } catch (AssertionError ex) {
            throw new DataTypeFormatException();
        }
    }

    public IntType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, INT_PATTERN);
        try {
            this.value = parseNumber(stringValue, format).intValueExact();
        } catch (AssertionError ex) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        IntType that = (IntType) other;
        return value.compareTo(that.getValue());
    }
}
