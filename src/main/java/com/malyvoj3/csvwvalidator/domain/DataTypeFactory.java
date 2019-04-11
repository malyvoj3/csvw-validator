package com.malyvoj3.csvwvalidator.domain;

import com.malyvoj3.csvwvalidator.domain.model.DataType;
import com.malyvoj3.csvwvalidator.domain.model.Format;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.*;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.date.*;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.duration.DayTimeDurationType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.duration.DurationType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.duration.YearMonthDurationType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.numeric.*;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.string.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataTypeFactory {

    private final static String DATA_TYPE_BASE_DEFAULT = "string";
    private final static String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-ddTHH:mm:ss";
    private final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    private final static String TIME_FORMAT_DEFAULT = "HH:mm:ss";
    private final static String TIMEZONE_SUFFIX = "XXX";

    public ValueType createDataType(String stringValue, DataType dataType) throws DataTypeFormatException {
        String base = dataType != null ? dataType.getBase() : null;
        base = base == null ? DATA_TYPE_BASE_DEFAULT : base;
        Format format = dataType != null ? dataType.getFormat() : null;
        if (CsvwKeywords.STRING_DATA_TYPE.equals(base) || CsvwKeywords.ANY_URI_DATA_TYPE.equals(base)
                || CsvwKeywords.JSON_DATA_TYPE.equals(base)
                || CsvwKeywords.HTML_DATA_TYPE.equals(base)
                || CsvwKeywords.XML_DATA_TYPE.equals(base)) {
            return createString(stringValue, format);
        } else if (CsvwKeywords.DATE_TIME_DATA_TYPE.equals(base) || CsvwKeywords.DATETIME_DATA_TYPE.equals(base)) {
            return createDateTime(stringValue, format);
        } else if (CsvwKeywords.DATE_TIME_STAMP_DATA_TYPE.equals(base)) {
            return createDateTimeStamp(stringValue, format);
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
        } else if (CsvwKeywords.DECIMAL_DATA_TYPE.equals(base)) {
            return createDecimal(stringValue, format);
        } else if (CsvwKeywords.DOUBLE_DATA_TYPE.equals(base)
                || CsvwKeywords.NUMBER_DATA_TYPE.equals(base)) {
            return createDouble(stringValue, format);
        } else if (CsvwKeywords.FLOAT_DATA_TYPE.equals(base)) {
            return createFloat(stringValue, format);
        } else if (CsvwKeywords.INTEGER_DATA_TYPE.equals(base)) {
            return createInteger(stringValue, format);
        } else if (CsvwKeywords.LONG_DATA_TYPE.equals(base)) {
            return createLong(stringValue, format);
        } else if (CsvwKeywords.INT_DATA_TYPE.equals(base)) {
            return createInt(stringValue, format);
        } else if (CsvwKeywords.SHORT_DATA_TYPE.equals(base)) {
            return createShort(stringValue, format);
        } else if (CsvwKeywords.BYTE_DATA_TYPE.equals(base)) {
            return createByte(stringValue, format);
        } else if (CsvwKeywords.NEGATIVE_INTEGER_DATA_TYPE.equals(base)) {
            return createNegativeInt(stringValue, format);
        } else if (CsvwKeywords.NON_NEGATIVE_INTEGER_DATA_TYPE.equals(base)) {
            return createNonNegativeInt(stringValue, format);
        } else if (CsvwKeywords.NON_POSITIVE_INTEGER_DATA_TYPE.equals(base)) {
            return createNonPositiveInt(stringValue, format);
        } else if (CsvwKeywords.POSITIVE_INTEGER_DATA_TYPE.equals(base)) {
            return createPositiveInt(stringValue, format);
        } else if (CsvwKeywords.UNSIGNED_LONG_DATA_TYPE.equals(base)) {
            return createUnsignedLong(stringValue, format);
        } else if (CsvwKeywords.UNSIGNED_INT_DATA_TYPE.equals(base)) {
            return createUnsignedInt(stringValue, format);
        } else if (CsvwKeywords.UNSIGNED_SHORT_DATA_TYPE.equals(base)) {
            return createUnsignedShort(stringValue, format);
        } else if (CsvwKeywords.UNSIGNED_BYTE_DATA_TYPE.equals(base)) {
            return createUnsignedByte(stringValue, format);
        } else {
            throw new RuntimeException();
        }
    }

    private ValueType createUnsignedLong(String stringValue, Format format) throws DataTypeFormatException {
        return new UnsignedLongType(stringValue, format);
    }

    private ValueType createUnsignedInt(String stringValue, Format format) throws DataTypeFormatException {
        return new UnsignedIntType(stringValue, format);
    }

    private ValueType createUnsignedShort(String stringValue, Format format) throws DataTypeFormatException {
        return new UnsignedShortType(stringValue, format);
    }

    private ValueType createUnsignedByte(String stringValue, Format format) throws DataTypeFormatException {
        return new UnsignedByteType(stringValue, format);
    }

    private ValueType createNegativeInt(String stringValue, Format format) throws DataTypeFormatException {
        return new NegativeIntegerType(stringValue, format);
    }

    private ValueType createNonNegativeInt(String stringValue, Format format) throws DataTypeFormatException {
        return new NonNegativeIntegerType(stringValue, format);
    }

    private ValueType createNonPositiveInt(String stringValue, Format format) throws DataTypeFormatException {
        return new NonPositiveIntegerType(stringValue, format);
    }

    private ValueType createPositiveInt(String stringValue, Format format) throws DataTypeFormatException {
        return new PositiveIntegerType(stringValue, format);
    }

    private ValueType createInteger(String stringValue, Format format) throws DataTypeFormatException {
        return new IntegerType(stringValue, format);
    }

    private ValueType createLong(String stringValue, Format format) throws DataTypeFormatException {
        return new LongType(stringValue, format);
    }

    private ValueType createInt(String stringValue, Format format) throws DataTypeFormatException {
        return new IntType(stringValue, format);
    }

    private ValueType createShort(String stringValue, Format format) throws DataTypeFormatException {
        return new ShortType(stringValue, format);
    }

    private ValueType createByte(String stringValue, Format format) throws DataTypeFormatException {
        return new ByteType(stringValue, format);
    }

    private ValueType createFloat(String stringValue, Format format) throws DataTypeFormatException {
        return new FloatType(stringValue, format);
    }

    private ValueType createDouble(String stringValue, Format format) throws DataTypeFormatException {
        return new DoubleType(stringValue, format);
    }

    private ValueType createDecimal(String stringValue, Format format) throws DataTypeFormatException {
        return new DecimalType(stringValue, format);
    }

    private ValueType createGDay(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new GDayType(stringValue, format);
        } else {
            result = new GDayType(stringValue);
        }
        return result;
    }

    private ValueType createGMonthDay(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new GMonthDayType(stringValue, format);
        } else {
            result = new GMonthDayType(stringValue);
        }
        return result;
    }

    private ValueType createGMonth(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new GMonthType(stringValue, format);
        } else {
            result = new GMonthType(stringValue);
        }
        return result;
    }

    private ValueType createGYear(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new GYearType(stringValue, format);
        } else {
            result = new GYearType(stringValue);
        }
        return result;
    }

    private ValueType createGYearMonth(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new GYearMonthType(stringValue, format);
        } else {
            result = new GYearMonthType(stringValue);
        }
        return result;
    }

    private ValueType createDuration(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new DurationType(stringValue, format);
        } else {
            result = new DurationType(stringValue);
        }
        return result;
    }

    private ValueType createDayTimeDuration(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new DayTimeDurationType(stringValue, format);
        } else {
            result = new DayTimeDurationType(stringValue);
        }
        return result;
    }

    private ValueType createYearMonthDuration(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new YearMonthDurationType(stringValue, format);
        } else {
            result = new YearMonthDurationType(stringValue);
        }
        return result;
    }

    private ValueType createNormalizedString(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new NormalizedStringType(stringValue, format);
        } else {
            result = new NormalizedStringType(stringValue);
        }
        return result;
    }

    private ValueType createToken(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new TokenType(stringValue, format);
        } else {
            result = new TokenType(stringValue);
        }
        return result;
    }

    private ValueType createLanguage(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new LanguageType(stringValue, format);
        } else {
            result = new LanguageType(stringValue);
        }
        return result;
    }

    private ValueType createName(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new NameType(stringValue, format);
        } else {
            result = new NameType(stringValue);
        }
        return result;
    }

    private ValueType createNMToken(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new NMTokenType(stringValue, format);
        } else {
            result = new NMTokenType(stringValue);
        }
        return result;
    }

    private ValueType createQName(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new QNameType(stringValue, format);
        } else {
            result = new QNameType(stringValue);
        }
        return result;
    }

    private ValueType createHexBinary(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new HexBinaryType(stringValue, format);
        } else {
            result = new HexBinaryType(stringValue);
        }
        return result;
    }

    private ValueType createBoolean(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new BooleanType(stringValue, format);
        } else {
            result = new BooleanType(stringValue);
        }
        return result;
    }

    private ValueType createBinary(String stringValue, Format formatObj) throws DataTypeFormatException {
        ValueType result;
        String format = getFormat(formatObj);
        if (format != null) {
            result = new BinaryType(stringValue, format);
        } else {
            result = new BinaryType(stringValue);
        }
        return result;
    }

    private ValueType createTime(String stringValue, Format formatObj) throws DataTypeFormatException {
        String timeFormat = getFormat(formatObj);
        timeFormat = timeFormat != null ? timeFormat : addFractionalSeconds(stringValue, TIME_FORMAT_DEFAULT);
        ValueType result;
        try {
            result = new TimeType(stringValue, timeFormat);
        } catch (DataTypeFormatException ex) {
            // try with timezone
            result = new TimeType(stringValue, timeFormat + TIMEZONE_SUFFIX);
        }
        return result;
    }

    private ValueType createDateTimeStamp(String stringValue, Format formatObj) throws DataTypeFormatException {
        String dateTimeStampFormat = getFormat(formatObj);
        dateTimeStampFormat = dateTimeStampFormat != null ? dateTimeStampFormat : addFractionalSeconds(stringValue, DATE_TIME_FORMAT_DEFAULT);
        return new DateTimeStampType(stringValue, dateTimeStampFormat + TIMEZONE_SUFFIX);
    }

    private ValueType createDateTime(String stringValue, Format formatObj) throws DataTypeFormatException {
        String dateTimeFormat = getFormat(formatObj);
        dateTimeFormat = dateTimeFormat != null ? dateTimeFormat : addFractionalSeconds(stringValue, DATE_TIME_FORMAT_DEFAULT);
        ValueType result;
        try {
            result = new DateTimeType(stringValue, dateTimeFormat);
        } catch (DataTypeFormatException ex) {
            // try with timezone
            result = new DateTimeType(stringValue, dateTimeFormat + TIMEZONE_SUFFIX);
        }
        return result;
    }

    private ValueType createDate(String stringValue, Format formatObj) throws DataTypeFormatException {
        String dateFormat = getFormat(formatObj);
        dateFormat = dateFormat != null ? dateFormat : addFractionalSeconds(stringValue, DATE_FORMAT_DEFAULT);
        ValueType result;
        try {
            result = new DateType(stringValue, dateFormat);
        } catch (DataTypeFormatException ex) {
            // try with timezone
            result = new DateType(stringValue, dateFormat + TIMEZONE_SUFFIX);
        }
        return result;
    }

    private ValueType createString(String stringValue, Format formatObj) throws DataTypeFormatException {
        String format = getFormat(formatObj);
        ValueType result;
        if (format != null) {
            result = new StringType(stringValue, format);
        } else {
            result = new StringType(stringValue);
        }
        return result;
    }

    private String getFormat(Format formatObj) {
        String format = null;
        if (formatObj != null) {
            format = formatObj.getPattern();
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
