package com.malyvoj3.csvwvalidator.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;

@UtilityClass
public class CsvwKeywords {


    public final HashMap<String, String> DATA_TYPES = new HashMap<>();

    public final String NATURAL_LANGUAGE_CODE = "und";
    public final String CSVW_VOCABULARY_URL = "http://www.w3.org/ns/csvw";

    public final String BASE_PROPERTY = "@base";
    public final String LANGUAGE_PROPERTY = "@language";
    public final String CONTEXT_PROPERTY = "@context";
    public final String VALUE_PROPERTY = "@value";
    public final String ID_PROPERTY = "@id";
    public final String TYPE_PROPERTY = "@type";

    // inherited properties
    public final String ABOUT_URL_PROPERTY = "aboutUrl";
    public final String DATATYPE_PROPERTY = "datatype";
    public final String DEFAULT_PROPERTY = "default";
    public final String LANG_PROPERTY = "lang";
    public final String NULL_PROPERTY = "null";
    public final String ORDERED_PROPERTY = "ordered";
    public final String PROPERTY_URL_PROPERTY = "propertyUrl";
    public final String REQUIRED_PROPERTY = "required";
    public final String SEPARATOR_PROPERTY = "separator";
    public final String TEXT_DIRECTION_PROPERTY = "textDirection";
    public final String VALUE_URL_PROPERTY = "valueUrl";

    // top-level properties
    public final String DIALECT_PROPERTY = "dialect";
    public final String NOTES_PROPERTY = "notes";
    public final String TABLE_DIRECTION_PROPERTY = "tableDirection";
    public final String TABLE_SCHEMA_PROPERTY = "tableSchema";
    public final String TRANSFORMATIONS_PROPERTY = "transformations";

    // table properties
    public final String URL_PROPERTY = "url";
    public final String SUPPRESS_OUTPUT_PROPERTY = "suppressOutput";

    // table group properties
    public final String TABLES_PROPERTY = "tables";

    // schema properties
    public final String COLUMNS_PROPERTY = "columns";
    public final String PRIMARY_KEY_PROPERTY = "primaryKey";
    public final String ROW_TITLES_PROPERTY = "rowTitles";
    public final String FOREIGN_KEYS_PROPERTY = "foreignKeys";

    // foreign key properties
    public final String COLUMN_REFERENCE_PROPERTY = "columnReference";
    public final String REFERENCE_PROPERTY = "reference";

    // reference properties
    public final String RESOURCE_PROPERTY = "resource";
    public final String SCHEMA_REFERENCE_PROPERTY = "schemaReference";

    // column properties
    public final String NAME_PROPERTY = "name";
    public final String TITLES_PROPERTY = "titles";
    public final String VIRTUAL_PROPERTY = "virtual";

    // dialect properties
    public final String COMMENT_PREFIX_PROPERTY = "commentPrefix";
    public final String DELIMITER_PROPERTY = "delimiter";
    public final String DOUBLE_QUOTE_PROPERTY = "doubleQuote";
    public final String ENCODING_PROPERTY = "encoding";
    public final String HEADER_PROPERTY = "header";
    public final String HEADER_ROW_COUNT_PROPERTY = "headerRowCount";
    public final String LINE_TERMINATORS_PROPERTY = "lineTerminators";
    public final String QUOTE_CHAR_PROPERTY = "quoteChar";
    public final String SKIP_BLANK_ROWS_PROPERTY = "skipBlankRows";
    public final String SKIP_COLUMNS_PROPERTY = "skipColumns";
    public final String SKIP_INITIAL_SPACE_PROPERTY = "skipInitialSpace";
    public final String SKIP_ROWS_PROPERTY = "skipRows";
    public final String TRIM_PROPERTY = "trim";

    // dataType properties
    public final String DATA_TYPE_BASE_PROPERTY = "base";
    public final String FORMAT_PROPERTY = "format";
    public final String LENGTH_PROPERTY = "length";
    public final String MIN_LENGTH_PROPERTY = "minLength";
    public final String MAX_LENGTH_PROPERTY = "maxLength";
    public final String MINIMUM_PROPERTY = "minimum";
    public final String MAXIMUM_PROPERTY = "maximum";
    public final String MIN_INCLUSIVE_PROPERTY = "minInclusive";
    public final String MAX_INCLUSIVE_PROPERTY = "maxInclusive";
    public final String MIN_EXCLUSIVE_PROPERTY = "minExclusive";
    public final String MAX_EXCLUSIVE_PROPERTY = "maxExclusive";

    // format properties
    public final String PATTERN_PROPERTY = "pattern";
    public final String DECIMAL_CHAR_PROPERTY = "decimalChar";
    public final String GROUP_CHAR_PROPERTY = "groupChar";

    public final String ANY_DATA_TYPE = "any";
    public final String ANY_ATOMIC_DATA_TYPE = "anyAtomicType";
    public final String ANY_URI_DATA_TYPE = "anyURI";
    public final String BASE_64_BINARY_DATA_TYPE = "base64Binary";
    public final String BINARY_DATA_TYPE = "binary";
    public final String BOOLEAN_DATA_TYPE = "boolean";
    public final String BYTE_DATA_TYPE = "byte";
    public final String DATE_DATA_TYPE = "date";
    public final String DATE_TIME_DATA_TYPE = "dateTime";
    public final String DATE_TIME_STAMP_DATA_TYPE = "dateTimeStamp";
    public final String DATETIME_DATA_TYPE = "datetime";
    public final String DAY_TIME_DURATION_DATA_TYPE = "dayTimeDuration";
    public final String DECIMAL_DATA_TYPE = "decimal";
    public final String DOUBLE_DATA_TYPE = "double";
    public final String DURATION_DATA_TYPE = "duration";
    public final String FLOAT_DATA_TYPE = "float";
    public final String G_DAY_DATA_TYPE = "gDay";
    public final String G_MONTH_DATA_TYPE = "gMonth";
    public final String G_MONTH_DAY_DATA_TYPE = "gMonthDay";
    public final String G_YEAR_DATA_TYPE = "gYear";
    public final String G_YEAR_MONTH_DATA_TYPE = "gYearMonth";
    public final String HTML_DATA_TYPE = "html";
    public final String HEX_BINARY_DATA_TYPE = "hexBinary";
    public final String INT_DATA_TYPE = "int";
    public final String INTEGER_DATA_TYPE = "integer";
    public final String JSON_DATA_TYPE = "json";
    public final String LANGUAGE_DATA_TYPE = "language";
    public final String LONG_DATA_TYPE = "long";
    public final String NAME_DATA_TYPE = "Name";
    public final String NCNAME_DATA_TYPE = "NCName";
    public final String NMTOKEN_DATA_TYPE = "NMTOKEN";
    public final String NEGATIVE_INTEGER_DATA_TYPE = "negativeInteger";
    public final String NON_NEGATIVE_INTEGER_DATA_TYPE = "nonNegativeInteger";
    public final String NON_POSITIVE_INTEGER_DATA_TYPE = "nonPositiveInteger";
    public final String NORMALIZED_STRING_DATA_TYPE = "normalizedString";
    public final String NUMBER_DATA_TYPE = "number";
    public final String QNAME_DATA_TYPE = "QName";
    public final String POSITIVE_INTEGER_DATA_TYPE = "positiveInteger";
    public final String SHORT_DATA_TYPE = "short";
    public final String STRING_DATA_TYPE = "string";
    public final String TIME_DATA_TYPE = "time";
    public final String TOKEN_DATA_TYPE = "token";
    public final String UNSIGNED_BYTE_DATA_TYPE = "unsignedByte";
    public final String UNSIGNED_INT_DATA_TYPE = "unsignedInt";
    public final String UNSIGNED_LONG_DATA_TYPE = "unsignedLong";
    public final String UNSIGNED_SHORT_DATA_TYPE = "unsignedShort";
    public final String XML_DATA_TYPE = "xml";
    public final String YEAR_MONTH_DURATION_DATA_TYPE = "yearMonthDuration";

    static {
        DATA_TYPES.put(ANY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#");
        DATA_TYPES.put(ANY_ATOMIC_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#");
        DATA_TYPES.put(ANY_URI_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#anyURI");
        DATA_TYPES.put(BASE_64_BINARY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#base64Binary");
        DATA_TYPES.put(BINARY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#base64Binary");
        DATA_TYPES.put(BOOLEAN_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#boolean");
        DATA_TYPES.put(BYTE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#byte");
        DATA_TYPES.put(DATE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#date");
        DATA_TYPES.put(DATE_TIME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dateTime");
        DATA_TYPES.put(DATE_TIME_STAMP_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dateTimeStamp");
        DATA_TYPES.put(DATETIME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dateTime");
        DATA_TYPES.put(DAY_TIME_DURATION_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#dayTimeDuration");
        DATA_TYPES.put(DECIMAL_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#decimal");
        DATA_TYPES.put(DOUBLE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#double");
        DATA_TYPES.put(DURATION_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#duration");
        DATA_TYPES.put(FLOAT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#float");
        DATA_TYPES.put(G_DAY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gDay");
        DATA_TYPES.put(G_MONTH_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gMonth");
        DATA_TYPES.put(G_MONTH_DAY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gMonthDay");
        DATA_TYPES.put(G_YEAR_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gYear");
        DATA_TYPES.put(G_YEAR_MONTH_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#gYearMonth");
        DATA_TYPES.put(HTML_DATA_TYPE, "http://www.w3.org/1999/02/22-rdf-syntax-ns#HTML");
        DATA_TYPES.put(HEX_BINARY_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#hexBinary");
        DATA_TYPES.put(INT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#int");
        DATA_TYPES.put(INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#integer");
        DATA_TYPES.put(JSON_DATA_TYPE, "http://www.w3.org/ns/csvw#JSON");
        DATA_TYPES.put(LANGUAGE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#language");
        DATA_TYPES.put(LONG_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#long");
        DATA_TYPES.put(NAME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#Name");
        DATA_TYPES.put(NCNAME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#NCName");
        DATA_TYPES.put(NMTOKEN_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#NMTOKEN");
        DATA_TYPES.put(NEGATIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#negativeInteger");
        DATA_TYPES.put(NON_NEGATIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#nonNegativeInteger");
        DATA_TYPES.put(NON_POSITIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#nonPositiveInteger");
        DATA_TYPES.put(NORMALIZED_STRING_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#normalizedString");
        DATA_TYPES.put(NUMBER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#double");
        DATA_TYPES.put(QNAME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#QName");
        DATA_TYPES.put(POSITIVE_INTEGER_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#positiveInteger");
        DATA_TYPES.put(SHORT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#short");
        DATA_TYPES.put(STRING_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#string");
        DATA_TYPES.put(TIME_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#time");
        DATA_TYPES.put(TOKEN_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#token");
        DATA_TYPES.put(UNSIGNED_BYTE_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedByte");
        DATA_TYPES.put(UNSIGNED_INT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedInt");
        DATA_TYPES.put(UNSIGNED_LONG_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedLong");
        DATA_TYPES.put(UNSIGNED_SHORT_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#unsignedShort");
        DATA_TYPES.put(XML_DATA_TYPE, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
        DATA_TYPES.put(YEAR_MONTH_DURATION_DATA_TYPE, "http://www.w3.org/2001/XMLSchema#yearMonthDuration");
    }
}
