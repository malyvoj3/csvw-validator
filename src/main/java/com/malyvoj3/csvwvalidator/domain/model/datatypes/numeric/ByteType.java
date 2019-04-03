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
public class ByteType extends NumericType {

    private static final String SHORT_PATTERN = "[\\-+]?[0-9]+";

    private Byte value;

    public ByteType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, SHORT_PATTERN);
        try {
            this.value = parseNumber(stringValue, null).byteValueExact();
        } catch (AssertionError ex) {
            throw new DataTypeFormatException();
        }
    }

    public ByteType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, SHORT_PATTERN);
        try {
            this.value = parseNumber(stringValue, format).byteValueExact();
        } catch (AssertionError ex) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        ByteType that = (ByteType) other;
        return value.compareTo(that.getValue());
    }
}
