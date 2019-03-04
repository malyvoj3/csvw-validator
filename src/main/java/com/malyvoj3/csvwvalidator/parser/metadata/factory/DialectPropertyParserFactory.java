package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect.*;

public class DialectPropertyParserFactory {

    public static PropertyParser<DialectDescription> createParser(String key) {
        switch (key) {
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
