package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DecimalType extends NumericType {

    private static final String DECIMAL_PATTERN = "(\\${percent}|\\${perMill})?(\\+|-)?(([0-9]+(\\${groupChar}[0-9]+)?)(\\${decimalChar})?(\\${decimalChar}[0-9]+(\\${groupChar}[0-9]+)?)?)(\\${percent}|\\${perMill})?";

    private BigDecimal value;

    public DecimalType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, null);
    }

    public DecimalType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, format);
    }

    private void construct(String stringValue, Format format) throws DataTypeFormatException {
        matchPattern(stringValue, resolveNumberPattern(DECIMAL_PATTERN, format));
        this.value = parseBigDecimal(stringValue, format);
    }

    @Override
    public String getCanonicalForm() {
        return value.toString();
    }

    @Override
    public int compareTo(@NonNull ValueType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DecimalType that = (DecimalType) other;
        return value.compareTo(that.getValue());
    }
}
