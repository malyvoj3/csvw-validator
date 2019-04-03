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
public class IntegerType extends NumericType {

    private static final String INTEGER_PATTERN = "[\\-+]?[0-9]+";

    private BigDecimal value;

    public IntegerType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, INTEGER_PATTERN);
        this.value = parseNumber(stringValue, null);
        if (!isInteger(this.value)) {
            throw new DataTypeFormatException();
        }
    }

    public IntegerType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, INTEGER_PATTERN);
        this.value = parseNumber(stringValue, format);
        if (!isInteger(this.value)) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        IntegerType that = (IntegerType) other;
        return value.compareTo(that.getValue());
    }
}
