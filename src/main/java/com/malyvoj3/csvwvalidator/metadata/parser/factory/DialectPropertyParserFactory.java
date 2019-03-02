package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect.*;

public class DialectPropertyParserFactory {

    public static PropertyParser<DialectDescription> createParser(String key) {
        switch (key) {
            case "commentPrefix":
                return new CommentPrefixPropertyParser<>();
            case "delimiter":
                return new DelimiterPropertyParser<>();
            case "doubleQuote":
                return new DoubleQuotePropertyParser<>();
            case "encoding":
                return new EncodingPropertyParser<>();
            case "header":
                return new HeaderPropertyParser<>();
            case "headerRowCount":
                return new HeaderRowCountPropertyParser<>();
            case "lineTerminators":
                return new LineTerminatorsPropertyParser<>();
            case "quoteChar":
                return new QuoteCharPropertyParser<>();
            case "skipBlankRows":
                return new SkipBlankRowsPropertyParser<>();
            case "skipColumns":
                return new SkipColumnsPropertyParser<>();
            case "skipInitialSpace":
                return new SkipInitialSpacePropertyParser<>();
            case "skipRows":
                return new SkipRowsPropertyParser<>();
            case "trim":
                return new TrimPropertyParser<>();
            default:
                return null;
        }
    }

}
