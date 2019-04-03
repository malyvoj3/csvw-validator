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
public class ShortType extends NumericType {

    private static final String SHORT_PATTERN = "[\\-+]?[0-9]+(\\${groupChar}[0-9]+)*";

    private Short value;

    public ShortType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, null);
    }

    public ShortType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, format);
    }

    private void construct(String stringValue, Format format) throws DataTypeFormatException {
        matchPattern(stringValue, resolveNumberPattern(SHORT_PATTERN, format));
        try {
            this.value = parseBigDecimal(stringValue, format).shortValueExact();
        } catch (ArithmeticException ex) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        ShortType that = (ShortType) other;
        return value.compareTo(that.getValue());
    }
}
