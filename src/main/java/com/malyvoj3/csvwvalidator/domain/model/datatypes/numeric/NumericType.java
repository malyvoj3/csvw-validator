package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.FormatParsingResult;
import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Optional;
import java.util.Properties;

public abstract class NumericType extends DataTypeDefinition {

    protected final static char PER_MILL_SIGN = '\u2030';
    protected final static char POSITIVE_SIGN = '+';
    protected final static char DECIMAL_SEPARATOR_DEFAULT = '.';
    protected final static char GROUP_CHAR_DEFAULT = ',';

    private final static String POSITIVE_INF = "INF";
    private final static String NEGATIVE_INF = "-INF";
    private final static String NAN = "NaN";

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

    protected FormatParsingResult parseNumber(String stringValue, Format numberFormat) throws DataTypeFormatException {
        try {
            FormatParsingResult result = new FormatParsingResult();
            if (POSITIVE_INF.equals(stringValue)) {
                result.setPosInf(true);
            } else if (NEGATIVE_INF.equals(stringValue)) {
                result.setNegInf(true);
            } else if (NAN.equals(stringValue)) {
                result.setNan(true);
            } else {
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
                    symbols.setGroupingSeparator(format.getGroupChar().charAt(0));
                } else {
                    symbols.setGroupingSeparator(GROUP_CHAR_DEFAULT);
                }

                if (StringUtils.isNotEmpty(format.getDecimalChar())) {
                    symbols.setDecimalSeparator(format.getDecimalChar().charAt(0));
                } else {
                    symbols.setDecimalSeparator(DECIMAL_SEPARATOR_DEFAULT);
                }

                if (StringUtils.isNotEmpty(pattern)) {
                    decimalFormat = new DecimalFormat(setDigitAfterExponent(pattern), symbols);
                } else {
                    decimalFormat = new DecimalFormat();
                    decimalFormat.setDecimalFormatSymbols(symbols);
                }
                decimalFormat.setParseBigDecimal(true);
                result.setValue((BigDecimal) decimalFormat.parse(toParse));
            }
            return result;
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    protected String resolvePattern(String pattern, Format format) {
        String groupChar = Optional.ofNullable(format)
                .map(Format::getGroupChar)
                .orElse(String.valueOf(GROUP_CHAR_DEFAULT));

        String decimalChar = Optional.ofNullable(format)
                .map(Format::getDecimalChar)
                .orElse(String.valueOf(DECIMAL_SEPARATOR_DEFAULT));

        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        Properties properties = new Properties();
        properties.setProperty("groupChar", groupChar);
        properties.setProperty("decimalChar", decimalChar);
        properties.setProperty("perMill", String.valueOf(PER_MILL_SIGN));
        return helper.replacePlaceholders(pattern, properties);
    }

    private String setDigitAfterExponent(String pattern) {
        String result = pattern;
        int index = pattern.indexOf("E");
        if (index > 0 && pattern.length() > index + 1 && pattern.charAt(index + 1) == '#') {
            result = pattern.substring(0, index) + "E0" + pattern.substring(index + 2);
        }
        return result;
    }

    protected BigDecimal parseBigDecimal(String stringValue, Format numberFormat) throws DataTypeFormatException {
        FormatParsingResult result = parseNumber(stringValue, numberFormat);
        if (result.getValue() == null) {
            throw new DataTypeFormatException();
        }
        return result.getValue();
    }

    protected boolean isInteger(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }

}
