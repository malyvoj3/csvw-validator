package com.malyvoj3.csvwvalidator.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CsvwKeywords {
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

}
