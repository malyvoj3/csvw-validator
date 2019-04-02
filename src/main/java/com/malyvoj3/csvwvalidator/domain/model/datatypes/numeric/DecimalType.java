package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class DecimalType extends DataTypeDefinition {

    private static final String DECIMAL_PATTERN = "(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)";

    private BigDecimal value;

    public DecimalType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, DECIMAL_PATTERN);
        this.value = new BigDecimal(stringValue);
    }

    public DecimalType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue);
        matchPattern(stringValue, DECIMAL_PATTERN);
        this.value = new BigDecimal(stringValue);
        // TODO NumericFormat
    }

    @Override
    public boolean isLengthDataType() {
        return false;
    }

    @Override
    public boolean isValueDataType() {
        return true;
    }

    @Override
    public String getCanonicalForm() {
        return value.toString();
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DecimalType that = (DecimalType) other;
        return value.compareTo(that.getValue());
    }
}
