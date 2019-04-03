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

    protected void validateFormatPattern(String stringValue, Format format) throws DataTypeFormatException {
        char decimalChar = Optional.ofNullable(format)
                .map(Format::getDecimalChar)
                .map(string -> string.charAt(0))
                .orElse(DECIMAL_SEPARATOR_DEFAULT);

        char groupChar = Optional.ofNullable(format)
                .map(Format::getGroupChar)
                .map(string -> string.charAt(0))
                .orElse(GROUP_CHAR_DEFAULT);

        String pattern = format.getPattern();
        String formatPattern = "(\\-|\\+)?[#0]+(\\${groupChar}[#0]+)*(\\${decimalChar}[#0]+(\\${groupChar}[#0]+)*(E[\\+\\-]?[#0]+(\\${groupChar}[#0]+)*)?)?(%|\u2030)?";
        matchPattern(pattern, resolveNumberPattern(formatPattern, format));

        boolean isPercent = false;
        boolean isPerMill = false;

        if (pattern.charAt(pattern.length() - 1) == '%') {
            pattern = pattern.substring(0, pattern.length() - 1);
            isPercent = true;
        } else if (pattern.charAt(pattern.length() - 1) == '\u2030') {
            pattern = pattern.substring(0, pattern.length() - 1);
            isPerMill = true;
        }

        String integerRegexp = null;
        String fractRegexp = null;
        String exponentRegexp = null;

        // Rozdelit na integer, fractional, exponent
        String integerPart = null;
        String fractionalPart = null;
        String exponentPart = null;

        String[] exponentDivide = pattern.split("E");
        if (exponentDivide.length > 1) {
            exponentPart = exponentDivide[1];
        }
        integerPart = exponentDivide[0];
        String[] fracDivide = integerPart.split(String.valueOf("\\" + decimalChar));
        if (fracDivide.length > 1) {
            integerPart = fracDivide[0];
            fractionalPart = fracDivide[1];
        }

        String escapedGroupChar = String.valueOf("\\" + groupChar);
        String optionalGroupCharRegex = String.format("(\\%s)?", groupChar);
        int optionalCounter = 0;
        int mandatoryCounter = 0;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < integerPart.length(); i++) {
            char tmp = integerPart.charAt(i);
            if (tmp == groupChar) {
                builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
                if (mandatoryCounter > 0) {
                    builder.append(escapedGroupChar);
                } else {
                    builder.append(optionalGroupCharRegex);
                }
                optionalCounter = 0;
                mandatoryCounter = 0;
            } else if (tmp == '#') {
                optionalCounter++;
            } else if (tmp == '0') {
                mandatoryCounter++;
            } else if (tmp == '-') {
                builder.append("\\-");
            } else if (tmp == '+') {
                builder.append("\\+");
            } else {
                throw new IllegalStateException();
            }
        }
        builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
        integerRegexp = builder.toString();

        if (fractionalPart != null) {
            optionalCounter = 0;
            mandatoryCounter = 0;
            builder = new StringBuilder();
            for (int i = 0; i < fractionalPart.length(); i++) {
                char tmp = fractionalPart.charAt(i);
                if (tmp == groupChar) {
                    builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
                    if (mandatoryCounter > 0) {
                        builder.append(escapedGroupChar);
                    } else {
                        builder.append(optionalGroupCharRegex);
                    }
                    optionalCounter = 0;
                    mandatoryCounter = 0;
                } else if (tmp == '#') {
                    optionalCounter++;
                } else if (tmp == '0') {
                    mandatoryCounter++;
                } else {
                    throw new IllegalStateException();
                }
            }
            builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
            fractRegexp = builder.toString();
        }

        if (exponentPart != null) {
            optionalCounter = 0;
            mandatoryCounter = 0;
            builder = new StringBuilder();
            for (int i = 0; i < exponentPart.length(); i++) {
                char tmp = exponentPart.charAt(i);
                if (tmp == groupChar) {
                    builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
                    if (mandatoryCounter > 0) {
                        builder.append(escapedGroupChar);
                    } else {
                        builder.append(optionalGroupCharRegex);
                    }
                    optionalCounter = 0;
                    mandatoryCounter = 0;
                } else if (tmp == '#') {
                    optionalCounter++;
                } else if (tmp == '0') {
                    mandatoryCounter++;
                } else if (tmp == '-') {
                    builder.append("\\-");
                } else if (tmp == '+') {
                    builder.append("\\+");
                } else {
                    throw new IllegalStateException();
                }
            }
            builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
            exponentRegexp = builder.toString();
        }

        String resultRegexp;
        if (integerRegexp != null && fractRegexp != null && exponentRegexp != null) {
            resultRegexp = integerRegexp + decimalChar + fractRegexp + "E" + exponentRegexp;
        } else if (integerRegexp != null && fractRegexp != null) {
            resultRegexp = integerRegexp + decimalChar + fractRegexp;
        } else {
            resultRegexp = integerRegexp;
        }
        if (isPercent) {
            resultRegexp = resultRegexp + "%";
        } else if (isPerMill) {
            resultRegexp = resultRegexp + "\u2030";
        }

        matchPattern(stringValue, resultRegexp);
    }

    private String getDigitGroup(int optionalCounter, int mandatoryCounter) {
        return String.format("[0-9]{%d,%d}", mandatoryCounter, mandatoryCounter + optionalCounter);
    }

    protected String resolveNumberPattern(String pattern, Format format) {
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
