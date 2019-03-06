package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.ColumnDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.NamePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.SuppressOutputPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.TitlesPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.VirtualPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class ColumnParserFactory<T extends ColumnDescription> extends InheritedParserFactory<T> {

    @Override
    public PropertyParser<T> createParser(String properyName) {
        PropertyParser<T> parser = super.createParser(properyName);
        if (parser != null) {
            return parser;
        }
        switch (properyName) {
            case CsvwKeywords.NAME_PROPERTY:
                return new NamePropertyParser<>();
            case CsvwKeywords.SUPPRESS_OUTPUT_PROPERTY:
                return new SuppressOutputPropertyParser<>();
            case CsvwKeywords.TITLES_PROPERTY:
                return new TitlesPropertyParser<>();
            case CsvwKeywords.VIRTUAL_PROPERTY:
                return new VirtualPropertyParser<>();
            default:
                return null;
        }
    }

}
