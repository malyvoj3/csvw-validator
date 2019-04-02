package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.date.DateTimeType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.date.TimeType;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class DataTypeCreator {

    private final static String DATA_TYPE_BASE_DEFAULT = "string";
    private final static String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-ddTHH:mm:ss";
    private final static String TIME_FORMAT_DEFAULT = "HH:mm:ss";

    public void test() throws DataTypeFormatException {

    }

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
        } else {
            // TODO
            throw new RuntimeException();
        }
    }

    private DataType createTime(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        String timeFormat = null;
        if (formatDesc != null && formatDesc.getPattern() != null) {
            timeFormat = formatDesc.getPattern().getValue();
        }
        timeFormat = timeFormat != null ? timeFormat : addFractionalSeconds(stringValue, TIME_FORMAT_DEFAULT);
        return new TimeType(stringValue, timeFormat);
    }

    private DataType createDateTime(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        String dateTimeFormat = null;
        if (formatDesc != null && formatDesc.getPattern() != null) {
            dateTimeFormat = formatDesc.getPattern().getValue();
        }
        dateTimeFormat = dateTimeFormat != null ? dateTimeFormat : addFractionalSeconds(stringValue, DATE_TIME_FORMAT_DEFAULT);
        return new DateTimeType(stringValue, dateTimeFormat);
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

    private DataType createString(String stringValue, FormatDescription formatDesc) throws DataTypeFormatException {
        return null;
    }


}
