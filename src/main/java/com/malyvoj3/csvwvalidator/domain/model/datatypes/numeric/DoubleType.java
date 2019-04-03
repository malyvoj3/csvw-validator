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
public class DoubleType extends NumericType {

    private static final String DOUBLE_PATTERN = "(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)([Ee](\\+|-)?[0-9]+)?|(\\+|-)?INF|NaN";

    private Double value;

    public DoubleType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, DOUBLE_PATTERN);
        this.value = parseNumber(stringValue, null).doubleValue();
    }

    public DoubleType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, DOUBLE_PATTERN);
        this.value = parseNumber(stringValue, format).doubleValue();
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DoubleType that = (DoubleType) other;
        return value.compareTo(that.getValue());
    }
}
