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

    private static final String LONG_PATTERN = "[\\-+]?[0-9]+(\\${groupChar}[0-9]+)*";

    private Long value;

    public LongType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, null);
    }

    public LongType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, format);
    }

    private void construct(String stringValue, Format format) throws DataTypeFormatException {
        matchPattern(stringValue, resolveNumberPattern(LONG_PATTERN, format));
        try {
            this.value = parseBigDecimal(stringValue, format).longValueExact();
        } catch (ArithmeticException ex) {
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
