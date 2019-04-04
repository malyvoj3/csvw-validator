package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.FormatParsingResult;
import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import org.springframework.util.PropertyPlaceholderHelper;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Properties;

public abstract class NumericType extends DataTypeDefinition {

    protected final static char PERCENT_SIGN = '%';
    protected final static char PER_MILL_SIGN = '\u2030';
    protected final static char POSITIVE_SIGN = '+';
    protected final static char NEGATIVE_SIGN = '-';
    protected final static char EXPONENT_SIGN = 'E';
    protected final static char HASH_DIGIT = '#';
    protected final static char ZERO_DIGIT = '0';
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
                char decimalChar = Optional.of(format)
                        .map(Format::getDecimalChar)
                        .map(string -> string.charAt(0))
                        .orElse(DECIMAL_SEPARATOR_DEFAULT);
                char groupChar = Optional.of(format)
                        .map(Format::getGroupChar)
                        .map(string -> string.charAt(0))
                        .orElse(GROUP_CHAR_DEFAULT);
                String toParse = stringValue.toUpperCase(); // Have E instead e.
                if (format.getPattern() != null) {
                    validateFormatPattern(toParse, format);
                }
                toParse = toParse.replaceAll(String.valueOf("\\") + groupChar, "");
                toParse = toParse.replace(decimalChar, '.');

                // Remove percent/perMill.
                boolean isPercent = false;
                boolean isPerMill = false;
                if (toParse.charAt(toParse.length() - 1) == PERCENT_SIGN) {
                    toParse = toParse.substring(0, toParse.length() - 1);
                    isPercent = true;
                } else if (toParse.charAt(toParse.length() - 1) == PER_MILL_SIGN) {
                    toParse = toParse.substring(0, toParse.length() - 1);
                    isPerMill = true;
                }

                BigDecimal bigDecimal = new BigDecimal(toParse);
                if (isPercent) {
                    bigDecimal = bigDecimal.movePointLeft(2);
                } else if (isPerMill) {
                    bigDecimal = bigDecimal.movePointLeft(3);
                }
                result.setValue(bigDecimal);
            }
            return result;
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    private void validateFormatPattern(String stringValue, Format format) throws DataTypeFormatException {
        char decimalChar = Optional.ofNullable(format)
                .map(Format::getDecimalChar)
                .map(string -> string.charAt(0))
                .orElse(DECIMAL_SEPARATOR_DEFAULT);

        char groupChar = Optional.ofNullable(format)
                .map(Format::getGroupChar)
                .map(string -> string.charAt(0))
                .orElse(GROUP_CHAR_DEFAULT);

        String pattern = format.getPattern();
        String formatPattern = "(\\-|\\+)?[#0]+(\\${groupChar}[#0]+)*(\\${decimalChar}[#0]+(\\${groupChar}[#0]+)*(E[\\+\\-]?[#0]+(\\${groupChar}[#0]+)*)?)?(${percent}|${perMill})?";
        matchPattern(pattern, resolveNumberPattern(formatPattern, format));

        boolean isPercent = false;
        boolean isPerMill = false;

        if (pattern.charAt(pattern.length() - 1) == PERCENT_SIGN) {
            pattern = pattern.substring(0, pattern.length() - 1);
            isPercent = true;
        } else if (pattern.charAt(pattern.length() - 1) == PER_MILL_SIGN) {
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

        String[] exponentDivide = pattern.split(String.valueOf(EXPONENT_SIGN));
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
            } else if (tmp == HASH_DIGIT) {
                optionalCounter++;
            } else if (tmp == ZERO_DIGIT) {
                mandatoryCounter++;
            } else if (tmp == NEGATIVE_SIGN) {
                builder.append("\\-");
            } else if (tmp == POSITIVE_SIGN) {
                builder.append("\\+");
            } else {
                throw new IllegalStateException();
            }
        }
        builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
        integerRegexp = builder.toString();

        boolean fractPartMandatory = false;
        if (fractionalPart != null) {
            optionalCounter = 0;
            mandatoryCounter = 0;
            builder = new StringBuilder();
            for (int i = fractionalPart.length() - 1; i >= 0; i--) {
                char tmp = fractionalPart.charAt(i);
                if (tmp == groupChar) {
                    builder.append(reverse(getDigitGroup(optionalCounter, mandatoryCounter)));
                    if (mandatoryCounter > 0) {
                        builder.append(reverse(escapedGroupChar));
                    } else {
                        builder.append(reverse(optionalGroupCharRegex));
                    }
                    optionalCounter = 0;
                    mandatoryCounter = 0;
                } else if (tmp == HASH_DIGIT) {
                    optionalCounter++;
                } else if (tmp == ZERO_DIGIT) {
                    fractPartMandatory = true;
                    mandatoryCounter++;
                } else {
                    throw new IllegalStateException();
                }
            }
            builder.append(reverse(getDigitGroup(optionalCounter, mandatoryCounter)));
            fractRegexp = builder.reverse().toString();
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
                } else if (tmp == HASH_DIGIT) {
                    optionalCounter++;
                } else if (tmp == ZERO_DIGIT) {
                    mandatoryCounter++;
                } else if (tmp == NEGATIVE_SIGN) {
                    builder.append("\\-");
                } else if (tmp == POSITIVE_SIGN) {
                    builder.append("\\+");
                } else {
                    throw new IllegalStateException();
                }
            }
            builder.append(getDigitGroup(optionalCounter, mandatoryCounter));
            exponentRegexp = builder.toString();
        }

        if (fractPartMandatory) {
            fractRegexp = decimalChar + fractRegexp;
        } else {
            fractRegexp = "(\\" + decimalChar + fractRegexp + ")?";
        }

        String resultRegexp;
        if (integerRegexp != null && fractRegexp != null && exponentRegexp != null) {
            resultRegexp = integerRegexp + fractRegexp + EXPONENT_SIGN + exponentRegexp;
        } else if (integerRegexp != null && fractRegexp != null) {
            resultRegexp = integerRegexp + fractRegexp;
        } else {
            resultRegexp = integerRegexp;
        }
        if (isPercent) {
            resultRegexp = resultRegexp + PERCENT_SIGN;
        } else if (isPerMill) {
            resultRegexp = resultRegexp + PER_MILL_SIGN;
        }

        matchPattern(stringValue, resultRegexp);
    }

    private String getDigitGroup(int optionalCounter, int mandatoryCounter) {
        //return String.format("[0-9]{%d,%d}", mandatoryCounter, mandatoryCounter + optionalCounter);
        return String.format("[0-9]{%d,}", mandatoryCounter);
    }

    private String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
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
        properties.setProperty("percent", String.valueOf(PERCENT_SIGN));
        return helper.replacePlaceholders(pattern, properties);
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
