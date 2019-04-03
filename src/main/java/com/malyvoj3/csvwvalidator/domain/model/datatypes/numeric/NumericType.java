package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public abstract class NumericType extends DataTypeDefinition {

    private final static char PER_MILL_SIGN = '\u2030';
    private final static char POSITIVE_SIGN = '+';
    private final static char DECIMAL_SEPARATOR_DEFAULT = '.';

    public NumericType(String stringValue) {
        super(stringValue);
    }

    @Override
    public boolean isLengthDataType() {
        return false;
    }

    @Override
    public boolean isValueDataType() {
        return true;
    }

    protected BigDecimal parseNumber(String stringValue, Format numberFormat) throws DataTypeFormatException {
        try {
            Format format = numberFormat != null ? numberFormat : Format.builder().build();
            String pattern = format.getPattern();
            String toParse = stringValue;
            if (stringValue.charAt(0) == POSITIVE_SIGN) {
                toParse = stringValue.substring(1);
                if (StringUtils.isNotEmpty(pattern) && pattern.charAt(0) == POSITIVE_SIGN) {
                    pattern = pattern.substring(1);
                }
            }

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setPerMill(PER_MILL_SIGN);
            DecimalFormat decimalFormat;

            if (StringUtils.isNotEmpty(format.getGroupChar())) {
                symbols.setGroupingSeparator(format.getDecimalChar().charAt(0));
            }

            if (StringUtils.isNotEmpty(format.getDecimalChar())) {
                symbols.setDecimalSeparator(format.getDecimalChar().charAt(0));
            } else {
                symbols.setDecimalSeparator(DECIMAL_SEPARATOR_DEFAULT);
            }

            if (StringUtils.isNotEmpty(pattern)) {
                decimalFormat = new DecimalFormat(pattern, symbols);
            } else {
                decimalFormat = new DecimalFormat();
                decimalFormat.setDecimalFormatSymbols(symbols);
            }
            decimalFormat.setParseBigDecimal(true);
            return (BigDecimal) decimalFormat.parse(toParse);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    protected boolean isInteger(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }

}
