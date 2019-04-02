package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.*;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.date.*;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.duration.DayTimeDurationType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.duration.DurationType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.duration.YearMonthDurationType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.string.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class DataTypeFactory {

    private final static String DATA_TYPE_BASE_DEFAULT = "string";
    private final static String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-ddTHH:mm:ss";
    private final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    private final static String TIME_FORMAT_DEFAULT = "HH:mm:ss";

    public DataType createDataType(String stringValue, DataTypeDescription dataTypeDescription) throws DataTypeFormatException {
        String base = dataTypeDescription.getBase() != null ? dataTypeDescription.getBase().getValue() : DATA_TYPE_BASE_DEFAULT;
        FormatDescription format = dataTypeDescription.getFormat() != null ? dataTypeDescription.getFormat().getValue() : null;
        if (CsvwKeywords.STRING_DATA_TYPE.equals(base) || CsvwKeywords.ANY_URI_DATA_TYPE.equals(base)
                || CsvwKeywords.JSON_DATA_TYPE.equals(base)
                || CsvwKeywords.HTML_DATA_TYPE.equals(base)
                || CsvwKeywords.XML_DATA_TYPE.equals(base)) {
            return createString(stringValue, format);
        } else if (CsvwKeywords.DATE_TIME_DATA_TYPE.equals(base) || CsvwKeywords.DATETIME__DATA_TYPE.equals(base)) {
            return createDateTime(stringValue, format);
        } else if (CsvwKeywords.TIME_DATA_TYPE.equals(base)) {
            return createTime(stringValue, format);
        } else if (CsvwKeywords.DATE_DATA_TYPE.equals(base)) {
            return createDate(stringValue, format);
        } else if (CsvwKeywords.BINARY_DATA_TYPE.equals(base)
                || CsvwKeywords.BASE_64_BINARY_DATA_TYPE.equals(base)) {
            return createBinary(stringValue, format);
        } else if (CsvwKeywords.BOOLEAN_DATA_TYPE.equals(base)) {
            return createBoolean(stringValue, format);
        } else if (CsvwKeywords.HEX_BINARY_DATA_TYPE.equals(base)) {
            return createHexBinary(stringValue, format);
        } else if (CsvwKeywords.NORMALIZED_STRING_DATA_TYPE.equals(base)) {
            return createNormalizedString(stringValue, format);
        } else if (CsvwKeywords.TOKEN_DATA_TYPE.equals(base)) {
            return createToken(stringValue, format);
        } else if (CsvwKeywords.LANGUAGE_DATA_TYPE.equals(base)) {
            return createLanguage(stringValue, format);
        } else if (CsvwKeywords.NAME_DATA_TYPE.equals(base)) {
            return createName(stringValue, format);
        } else if (CsvwKeywords.NMTOKEN_DATA_TYPE.equals(base)) {
            return createNMToken(stringValue, format);
        } else if (CsvwKeywords.QNAME_DATA_TYPE.equals(base)) {
            return createQName(stringValue, format);
        } else if (CsvwKeywords.DURATION_DATA_TYPE.equals(base)) {
            return createDuration(stringValue, format);
        } else if (CsvwKeywords.DAY_TIME_DURATION_DATA_TYPE.equals(base)) {
            return createDayTimeDuration(stringValue, format);
        } else if (CsvwKeywords.YEAR_MONTH_DURATION_DATA_TYPE.equals(base)) {
            return createYearMonthDuration(stringValue, format);
        } else if (CsvwKeywords.G_DAY_DATA_TYPE.equals(base)) {
            return createGDay(stringValue, format);
        } else if (CsvwKeywords.G_MONTH_DAY_DATA_TYPE.equals(base)) {
            return createGMonthDay(stringValue, format);
        } else if (CsvwKeywords.G_MONTH_DATA_TYPE.equals(base)) {
            return createGMonth(stringValue, format);
        } else if (CsvwKeywords.G_YEAR_DATA_TYPE.equals(base)) {
            return createGYear(stringValue, format);
        } else if (CsvwKeywords.G_YEAR_MONTH_DATA_TYPE.equals(base)) {
            return createGYearMonth(stringValue, format);
        } else {
            throw new RuntimeException();
        }
    }

    private DataType createGDay(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new GDayType(stringValue, format);
        } else {
            result = new GDayType(stringValue);
        }
        return result;
    }

    private DataType createGMonthDay(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new GMonthDayType(stringValue, format);
        } else {
            result = new GMonthDayType(stringValue);
        }
        return result;
    }

    private DataType createGMonth(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new GMonthType(stringValue, format);
        } else {
            result = new GMonthType(stringValue);
        }
        return result;
    }

    private DataType createGYear(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new GYearType(stringValue, format);
        } else {
            result = new GYearType(stringValue);
        }
        return result;
    }

    private DataType createGYearMonth(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new GYearMonthType(stringValue, format);
        } else {
            result = new GYearMonthType(stringValue);
        }
        return result;
    }

    private DataType createDuration(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new DurationType(stringValue, format);
        } else {
            result = new DurationType(stringValue);
        }
        return result;
    }

    private DataType createDayTimeDuration(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new DayTimeDurationType(stringValue, format);
        } else {
            result = new DayTimeDurationType(stringValue);
        }
        return result;
    }

    private DataType createYearMonthDuration(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new YearMonthDurationType(stringValue, format);
        } else {
            result = new YearMonthDurationType(stringValue);
        }
        return result;
    }

    private DataType createNormalizedString(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new NormalizedStringType(stringValue, format);
        } else {
            result = new NormalizedStringType(stringValue);
        }
        return result;
    }

    private DataType createToken(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new TokenType(stringValue, format);
        } else {
            result = new TokenType(stringValue);
        }
        return result;
    }

    private DataType createLanguage(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new LanguageType(stringValue, format);
        } else {
            result = new LanguageType(stringValue);
        }
        return result;
    }

    private DataType createName(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new NameType(stringValue, format);
        } else {
            result = new NameType(stringValue);
        }
        return result;
    }

    private DataType createNMToken(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new NMTokenType(stringValue, format);
        } else {
            result = new NMTokenType(stringValue);
        }
        return result;
    }

    private DataType createQName(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new QNameType(stringValue, format);
        } else {
            result = new QNameType(stringValue);
        }
        return result;
    }

    private DataType createHexBinary(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new HexBinaryType(stringValue, format);
        } else {
            result = new HexBinaryType(stringValue);
        }
        return result;
    }

    private DataType createBoolean(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new BooleanType(stringValue, format);
        } else {
            result = new BooleanType(stringValue);
        }
        return result;
    }

    private DataType createBinary(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        DataType result;
        String format = getFormat(formatDesc);
        if (format != null) {
            result = new BinaryType(stringValue, format);
        } else {
            result = new BinaryType(stringValue);
        }
        return result;
    }

    private DataType createTime(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        String timeFormat = getFormat(formatDesc);
        timeFormat = timeFormat != null ? timeFormat : addFractionalSeconds(stringValue, TIME_FORMAT_DEFAULT);
        return new TimeType(stringValue, timeFormat);
    }

    private DataType createDateTime(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        String dateTimeFormat = getFormat(formatDesc);
        dateTimeFormat = dateTimeFormat != null ? dateTimeFormat : addFractionalSeconds(stringValue, DATE_TIME_FORMAT_DEFAULT);
        return new DateTimeType(stringValue, dateTimeFormat);
    }

    private DataType createDate(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        String dateFormat = getFormat(formatDesc);
        dateFormat = dateFormat != null ? dateFormat : addFractionalSeconds(stringValue, DATE_FORMAT_DEFAULT);
        return new DateType(stringValue, dateFormat);
    }

    private DataType createString(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        String format = getFormat(formatDesc);
        DataType result;
        if (format != null) {
            result = new StringType(stringValue, format);
        } else {
            result = new StringType(stringValue);
        }
        return result;
    }

    private String getFormat(FormatDescription formatDesc) {
        String format = null;
        if (formatDesc != null && formatDesc.getPattern() != null) {
            format = formatDesc.getPattern().getValue();
        }
        return format;
    }

    private String addFractionalSeconds(String stringValue, String format) {
        StringBuilder resultFormat = new StringBuilder(format);
        int index = stringValue.indexOf(".");
        if (index > 0) {
            resultFormat.append(".");
            while (Character.isDigit(stringValue.charAt(++index))) {
                resultFormat.append("S");
            }
        }
        return resultFormat.toString();
    }


}
