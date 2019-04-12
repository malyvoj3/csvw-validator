package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class DialectParserFactory<T extends DialectDescription> extends ObjectDescriptionParserFactory<T> {

    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> propertyParser = super.createParser(propertyName);
        if (propertyParser != null) {
            return propertyParser;
        }
        switch (propertyName) {
            case CsvwKeywords.COMMENT_PREFIX_PROPERTY:
                return new CommentPrefixPropertyParser<>();
            case CsvwKeywords.DELIMITER_PROPERTY:
                return new DelimiterPropertyParser<>();
            case CsvwKeywords.DOUBLE_QUOTE_PROPERTY:
                return new DoubleQuotePropertyParser<>();
            case CsvwKeywords.ENCODING_PROPERTY:
                return new EncodingPropertyParser<>();
            case CsvwKeywords.HEADER_PROPERTY:
                return new HeaderPropertyParser<>();
            case CsvwKeywords.HEADER_ROW_COUNT_PROPERTY:
                return new HeaderRowCountPropertyParser<>();
            case CsvwKeywords.LINE_TERMINATORS_PROPERTY:
                return new LineTerminatorsPropertyParser<>();
            case CsvwKeywords.QUOTE_CHAR_PROPERTY:
                return new QuoteCharPropertyParser<>();
            case CsvwKeywords.SKIP_BLANK_ROWS_PROPERTY:
                return new SkipBlankRowsPropertyParser<>();
            case CsvwKeywords.SKIP_COLUMNS_PROPERTY:
                return new SkipColumnsPropertyParser<>();
            case CsvwKeywords.SKIP_INITIAL_SPACE_PROPERTY:
                return new SkipInitialSpacePropertyParser<>();
            case CsvwKeywords.SKIP_ROWS_PROPERTY:
                return new SkipRowsPropertyParser<>();
            case CsvwKeywords.TRIM_PROPERTY:
                return new TrimPropertyParser<>();
            default:
                return null;
        }
    }

}
