package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.FormatParsingResult;
import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class FloatType extends NumericType {

    private static final String FLOAT_PATTERN = "(\\+|-)?(([0-9]+(\\${groupChar}[0-9]+)?)(\\${decimalChar})?(\\${decimalChar}[0-9]+(\\${groupChar}[0-9]+)?)?)([Ee](\\+|-)?[0-9]+)?|(\\+|-)?INF|NaN";

    private Float value;

    public FloatType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, null);
    }

    public FloatType(String stringValue, Format format) throws DataTypeFormatException {
        super(stringValue);
        construct(stringValue, format);
    }

    private void construct(String stringValue, Format format) throws DataTypeFormatException {
        matchPattern(stringValue, resolvePattern(FLOAT_PATTERN, format));
        FormatParsingResult result = parseNumber(stringValue, format);
        if (result.isNegInf()) {
            this.value = Float.NEGATIVE_INFINITY;
        } else if (result.isPosInf()) {
            this.value = Float.POSITIVE_INFINITY;
        } else if (result.isNan()) {
            this.value = Float.NaN;
        } else {
            this.value = result.getValue().floatValue();
        }
    }

    @Override
    public int compareTo(@NonNull DataTypeDefinition other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        FloatType that = (FloatType) other;
        return value.compareTo(that.getValue());
    }
}
