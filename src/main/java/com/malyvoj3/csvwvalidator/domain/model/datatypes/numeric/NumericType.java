package com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric;

import com.malyvoj3.csvwvalidator.domain.FormatParsingResult;
import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.math.BigDecimal;
import java.util.*;

public abstract class NumericType extends DataTypeDefinition {

    protected final static char PERCENT_SIGN = '%';
    protected final static char PER_MILL_SIGN = '\u2030';
    protected final static char EXPONENT_SIGN = 'E';
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
                String pattern = format.getPattern();

                // Remove percent/perMill.
                boolean isPercent = false;
                boolean isPerMill = false;
                int indexOfPercent = toParse.indexOf(PERCENT_SIGN);
                int indexOfPerMill = toParse.indexOf(PER_MILL_SIGN);
                if (indexOfPercent == 0 || indexOfPercent == toParse.length() - 1) {
                    isPercent = true;
                    toParse = toParse.replace(String.valueOf(PERCENT_SIGN), "");
                    if (pattern != null && pattern.indexOf(PERCENT_SIGN) == indexOfPercent) {
                        format.setPattern(pattern.replace(String.valueOf(PERCENT_SIGN), ""));
                    }
                } else if (indexOfPerMill == 0 || indexOfPerMill == toParse.length() - 1) {
                    isPerMill = true;
                    toParse = toParse.replace(String.valueOf(PER_MILL_SIGN), "");
                    if (pattern != null && pattern.indexOf(PER_MILL_SIGN) == indexOfPerMill) {
                        format.setPattern(pattern.replace(String.valueOf(PER_MILL_SIGN), ""));
                    }
                }
                if (format.getPattern() != null) {
                    validateFormatPattern(toParse, format);
                }
                toParse = toParse.replaceAll(String.valueOf("\\") + groupChar, "");
                toParse = toParse.replace(decimalChar, '.');

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
        String escapedDecimalChar = String.valueOf("\\" + decimalChar);

        String integerSign = getEscapedSign(integerPart);
        if (StringUtils.isNotEmpty(integerSign)) {
            integerPart = integerPart.substring(1);
        }
        String prefix;
        if (StringUtils.isNotEmpty(integerSign)) {
            prefix = integerSign;
        } else {
            prefix = "(\\+|\\-)?";
        }

        int minIntegerDigits = integerPart.replaceAll(String.valueOf("[\\#\\") + groupChar + "]", "").length();
        int allIntegerDigits = integerPart.replaceAll(String.valueOf("\\") + groupChar, "").length();
        List<String> integerParts = Arrays.asList(integerPart.split(String.valueOf(groupChar)));
        integerParts = slice(integerParts, 1, integerParts.size() - 1);
        int primaryGroupingSize = integerParts.size() > 0 ? integerParts.get(integerParts.size() - 1).length() : 0;
        int secondaryGroupingSize = integerParts.size() <= 1 ? primaryGroupingSize : integerParts.get(integerParts.size() - 2).length();

        String requiredDigits = null;
        String optionalDigits = null;

        List<String> integerParts2 = new ArrayList<>();
        String finalInteger;
        if (primaryGroupingSize == 0) {
            finalInteger = getUnlimitedDigitGroup(minIntegerDigits);
        } else {
            int integerRem = 0;
            while (minIntegerDigits > 0) {
                int sz = Math.min(primaryGroupingSize, minIntegerDigits);
                integerRem = primaryGroupingSize - sz;
                integerParts2.add(getExactDigitGroup(sz));
                minIntegerDigits -= sz;
                allIntegerDigits -= sz;
                primaryGroupingSize = secondaryGroupingSize;
            }
            Collections.reverse(integerParts2);
            requiredDigits = StringUtils.join(integerParts2, escapedGroupChar);
            if (allIntegerDigits > 0) {
                integerParts2 = new ArrayList<>();
                while (integerRem > 0) {
                    integerParts2.add("[0-9]");
                    integerRem--;
                }
                if (secondaryGroupingSize != primaryGroupingSize) {
                    primaryGroupingSize = secondaryGroupingSize;
                    integerRem = primaryGroupingSize - 1;
                    integerParts2.add("[0-9]" + escapedGroupChar);
                    while (integerRem > 0) {
                        integerParts2.add("[0-9]");
                        integerRem--;
                    }
                }
                if (integerParts2.isEmpty()) {
                    optionalDigits = "(?:" + getDigitGroup(1, primaryGroupingSize) + escapedGroupChar +
                            ")?(?:" + getExactDigitGroup(primaryGroupingSize) + escapedGroupChar + ")*";
                } else {
                    int lastIndex = integerParts2.size() - 1;
                    integerParts2.set(lastIndex, "(?:" + getDigitGroup(1, primaryGroupingSize) + escapedGroupChar +
                            ")?(?:" + getExactDigitGroup(primaryGroupingSize) + escapedGroupChar + ")*"
                            + integerParts2.get(lastIndex));
                    Collections.reverse(integerParts2);
                    StringBuilder tmpBuilder = new StringBuilder();
                    for (String string : integerParts2) {
                        tmpBuilder = new StringBuilder("(?:" + tmpBuilder + string + ")?");
                    }
                    optionalDigits = tmpBuilder.toString();
                }
                finalInteger = optionalDigits + requiredDigits;
            } else {
                finalInteger = requiredDigits;
            }
        }

        requiredDigits = null;
        optionalDigits = null;

        String finalFractional = null;
        if (fractionalPart != null) {
            List<String> fractionalParts = Arrays.asList(fractionalPart.split(String.valueOf(groupChar)));
            fractionalParts = slice(fractionalParts, 0, fractionalParts.size() - 2);
            int fractionalGroupingSize = fractionalParts.size() > 0 ? fractionalParts.get(0).length() : 0;
            int minFractionalDigits = fractionalPart.replaceAll(String.valueOf("[\\#\\") + groupChar + "]", "").length();
            int maxFractionalDigits = fractionalPart.replaceAll(String.valueOf("\\") + groupChar, "").length();
            if (fractionalGroupingSize == 0) {
                finalFractional = minFractionalDigits == maxFractionalDigits ?
                        getExactDigitGroup(maxFractionalDigits) : getDigitGroup(minFractionalDigits, maxFractionalDigits);
            } else {
                fractionalParts = new ArrayList<>();
                int fractionalRem = 0;
                while (minFractionalDigits > 0) {
                    int sz = Math.min(fractionalGroupingSize, minFractionalDigits);
                    fractionalRem = fractionalGroupingSize - sz;
                    fractionalParts.add(getExactDigitGroup(sz));
                    maxFractionalDigits -= sz;
                    minFractionalDigits -= sz;
                }
                requiredDigits = StringUtils.join(fractionalParts, escapedGroupChar);
                fractionalParts = new ArrayList<>();
                while (maxFractionalDigits > 0) {
                    fractionalParts.add(fractionalRem == 0 ? escapedGroupChar + "[0-9]" : "[0-9]");
                    maxFractionalDigits--;
                    fractionalRem = (fractionalRem - 1) % fractionalGroupingSize;
                }
                Collections.reverse(fractionalParts);
                StringBuilder tmpBuilder = new StringBuilder();
                for (String string : fractionalParts) {
                    tmpBuilder = new StringBuilder("(?:" + string + tmpBuilder + ")?");
                }
                optionalDigits = tmpBuilder.toString();
                finalFractional = requiredDigits + optionalDigits;
            }
            if (StringUtils.isNotEmpty(finalFractional)) {
                finalFractional = escapedDecimalChar + finalFractional;
                if (maxFractionalDigits > 0 && minFractionalDigits == 0) {
                    finalFractional = "(?:" + finalFractional + ")?";
                }
            }
        }

        String finalExponent = null;
        if (exponentPart != null) {
            String escapedExponentSign = getEscapedSign(exponentPart);
            int minExponentDigits = exponentPart.replaceAll(String.valueOf("[#\\-\\+\\") + groupChar + "]", "").length();
            int maxExponentDigits = exponentPart.replaceAll("[+\\-]", "").length();
            if (maxExponentDigits > 0 && maxExponentDigits == minExponentDigits) {
                finalExponent = "E" + escapedExponentSign + getExactDigitGroup(maxExponentDigits);
            } else if (maxExponentDigits > 0) {
                finalExponent = "E" + escapedExponentSign + getDigitGroup(minExponentDigits, maxExponentDigits);
            } else if (minExponentDigits > 0) {
                finalExponent = "E" + escapedExponentSign + getDigitGroup(minExponentDigits, maxExponentDigits);
            }
        }

        String resultRegexp;
        if (finalInteger != null && finalFractional != null && finalExponent != null) {
            resultRegexp = prefix + finalInteger + finalFractional + finalExponent;
        } else if (finalInteger != null && finalFractional != null) {
            resultRegexp = prefix + finalInteger + finalFractional;
        } else {
            resultRegexp = prefix + finalInteger;
        }
        matchPattern(stringValue, resultRegexp);
    }

    private String getEscapedSign(String string) {
        String escapedSign = "";
        if (string.charAt(0) == '+') {
            escapedSign = "\\+";
        } else if (string.charAt(0) == '-') {
            escapedSign = "\\-";
        }
        return escapedSign;
    }

    private List<String> slice(List<String> listToSlice, int startIndex, int endIndex) {
        int length = listToSlice.size();
        List<String> result;
        if (startIndex > length - 1 || endIndex > length - 1 || startIndex > endIndex) {
            result = new ArrayList<>();
        } else {
            result = listToSlice.subList(startIndex, endIndex + 1);
        }
        return result;
    }

    private String getUnlimitedDigitGroup(int mandatoryCounter) {
        return String.format("[0-9]{%d,}", mandatoryCounter);
    }

    private String getDigitGroup(int mandatoryCounter, int optionalCounter) {
        return String.format("[0-9]{%d,%d}", mandatoryCounter, optionalCounter);
    }

    private String getExactDigitGroup(int mandatoryCounter) {
        return String.format("[0-9]{%d}", mandatoryCounter);
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
