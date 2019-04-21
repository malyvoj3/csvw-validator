package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype.DecimalCharPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype.GroupCharPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype.PatternPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class FormatParserFactory<T extends FormatDescription> implements ParserFactory<T> {

    @Override
    public PropertyParser<T> createParser(String propertyName) {
        switch (propertyName) {
            case CsvwKeywords.PATTERN_PROPERTY:
                return new PatternPropertyParser<>();
            case CsvwKeywords.DECIMAL_CHAR_PROPERTY:
                return new DecimalCharPropertyParser<>();
            case CsvwKeywords.GROUP_CHAR_PROPERTY:
                return new GroupCharPropertyParser<>();
            default:
                return null;
        }
    }

}
