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
public class NonPositiveIntegerType extends IntegerType {

    public NonPositiveIntegerType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct();
    }

    public NonPositiveIntegerType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue, format);
        construct();
    }

    private void construct() throws DataTypeFormatException {
        if (getValue().compareTo(new BigDecimal(0)) > 0) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        NonPositiveIntegerType that = (NonPositiveIntegerType) other;
        return getValue().compareTo(that.getValue());
    }

}
