package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.Getter;

import static com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeConstraintGroup.*;

@Getter
public enum DataTypeDefinition {

    ANY_DATA_TYPE(CsvwKeywords.ANY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#", LENGTH, DataTypeGroup.STRING),
    ANY_ATOMIC(CsvwKeywords.ANY_ATOMIC_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#", LENGTH, DataTypeGroup.STRING),
    ANY_URI(CsvwKeywords.ANY_URI_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#anyURI", LENGTH, DataTypeGroup.STRING),
    BASE_64_BINARY(CsvwKeywords.BASE_64_BINARY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#base64Binary", LENGTH, DataTypeGroup.BINARY),
    BINARY(CsvwKeywords.BINARY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#base64Binary", LENGTH, DataTypeGroup.BINARY),
    BOOLEAN(CsvwKeywords.BOOLEAN_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#boolean", NONE, DataTypeGroup.BOOLEAN),
    BYTE(CsvwKeywords.BYTE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#byte", VALUE, DataTypeGroup.NUMERIC),
    DATE(CsvwKeywords.DATE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#date", VALUE, DataTypeGroup.DATE),
    DATE_TIME(CsvwKeywords.DATE_TIME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dateTime", VALUE, DataTypeGroup.DATETIME),
    DATE_TIME_STAMP(CsvwKeywords.DATE_TIME_STAMP_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dateTimeStamp", VALUE, DataTypeGroup.DATETIME),
    DATETIME(CsvwKeywords.DATETIME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dateTime", VALUE, DataTypeGroup.DATETIME),
    DAY_TIME_DURATION(CsvwKeywords.DAY_TIME_DURATION_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dayTimeDuration", VALUE, DataTypeGroup.DURATION),
    DECIMAL(CsvwKeywords.DECIMAL_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#decimal", VALUE, DataTypeGroup.NUMERIC),
    DOUBLE(CsvwKeywords.DOUBLE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#double", VALUE, DataTypeGroup.NUMERIC),
    DURATION(CsvwKeywords.DURATION_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#duration", VALUE, DataTypeGroup.DURATION),
    FLOAT(CsvwKeywords.FLOAT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#float", VALUE, DataTypeGroup.NUMERIC),
    G_DAY(CsvwKeywords.G_DAY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gDay", VALUE, DataTypeGroup.DATE),
    G_MONTH(CsvwKeywords.G_MONTH_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gMonth", VALUE, DataTypeGroup.DATE),
    G_MONTH_DAY(CsvwKeywords.G_MONTH_DAY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gMonthDay", VALUE, DataTypeGroup.DATE),
    G_YEAR(CsvwKeywords.G_YEAR_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gYear", VALUE, DataTypeGroup.DATE),
    G_YEAR_MONTH(CsvwKeywords.G_YEAR_MONTH_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gYearMonth", VALUE, DataTypeGroup.DATE),
    HTML(CsvwKeywords.HTML_DATA_TYPE, "http://www.w3.org/1999/02/22-rdf-syntax-ns#HTML", LENGTH, DataTypeGroup.STRING),
    HEX_BINARY(CsvwKeywords.HEX_BINARY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#hexBinary", LENGTH, DataTypeGroup.BINARY),
    INT(CsvwKeywords.INT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#int", VALUE, DataTypeGroup.NUMERIC),
    INTEGER(CsvwKeywords.INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#integer", VALUE, DataTypeGroup.NUMERIC),
    JSON(CsvwKeywords.JSON_DATA_TYPE, "http://www.w3.org/ns/csvw#JSON", LENGTH, DataTypeGroup.STRING),
    LANGUAGE(CsvwKeywords.LANGUAGE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#language", LENGTH, DataTypeGroup.STRING),
    LONG(CsvwKeywords.LONG_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#long", VALUE, DataTypeGroup.NUMERIC),
    NAME(CsvwKeywords.NAME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#Name", LENGTH, DataTypeGroup.STRING),
    NCNAME(CsvwKeywords.NCNAME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#NCName", LENGTH, DataTypeGroup.STRING),
    NMTOKEN(CsvwKeywords.NMTOKEN_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#NMTOKEN", LENGTH, DataTypeGroup.STRING),
    NEGATIVE_INTEGER(CsvwKeywords.NEGATIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#negativeInteger", VALUE, DataTypeGroup.NUMERIC),
    NON_NEGATIVE_INTEGER(CsvwKeywords.NON_NEGATIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#nonNegativeInteger", VALUE, DataTypeGroup.NUMERIC),
    NON_POSITIVE_INTEGER(CsvwKeywords.NON_POSITIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#nonPositiveInteger", VALUE, DataTypeGroup.NUMERIC),
    NORMALIZED_STRING(CsvwKeywords.NORMALIZED_STRING_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#normalizedString", LENGTH, DataTypeGroup.STRING),
    NUMBER(CsvwKeywords.NUMBER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#double", VALUE, DataTypeGroup.NUMERIC),
    QNAME(CsvwKeywords.QNAME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#QName", LENGTH, DataTypeGroup.STRING),
    POSITIVE_INTEGER(CsvwKeywords.POSITIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#positiveInteger", VALUE, DataTypeGroup.NUMERIC),
    SHORT(CsvwKeywords.SHORT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#short", VALUE, DataTypeGroup.NUMERIC),
    STRING(CsvwKeywords.STRING_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#string", LENGTH, DataTypeGroup.STRING),
    TIME(CsvwKeywords.TIME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#time", VALUE, DataTypeGroup.TIME),
    TOKEN(CsvwKeywords.TOKEN_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#token", LENGTH, DataTypeGroup.STRING),
    UNSIGNED_BYTE(CsvwKeywords.UNSIGNED_BYTE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedByte", VALUE, DataTypeGroup.NUMERIC),
    UNSIGNED_INT(CsvwKeywords.UNSIGNED_INT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedInt", VALUE, DataTypeGroup.NUMERIC),
    UNSIGNED_LONG(CsvwKeywords.UNSIGNED_LONG_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedLong", VALUE, DataTypeGroup.NUMERIC),
    UNSIGNED_SHORT(CsvwKeywords.UNSIGNED_SHORT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedShort", VALUE, DataTypeGroup.NUMERIC),
    XML(CsvwKeywords.XML_DATA_TYPE, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral", LENGTH, DataTypeGroup.STRING),
    YEAR_MONTH_DURATION(CsvwKeywords.YEAR_MONTH_DURATION_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#yearMonthDuration", VALUE, DataTypeGroup.DURATION);

    private String name;
    private String url;
    private DataTypeConstraintGroup constraintGroup;
    private DataTypeGroup group;

    DataTypeDefinition(String name, String url, DataTypeConstraintGroup constraintGroup, DataTypeGroup group) {
        this.name = name;
        this.url = url;
        this.constraintGroup = constraintGroup;
        this.group = group;
    }

}
