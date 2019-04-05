package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DataTypeDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.NamePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.SuppressOutputPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.TitlesPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column.VirtualPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class ColumnParserFactory<T extends ColumnDescription> extends InheritedParserFactory<T> {

    public ColumnParserFactory(DataTypeDescriptionParser dataTypeDescriptionParser) {
        super(dataTypeDescriptionParser);
    }

    @Override
    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> parser = super.createParser(propertyName);
        if (parser != null) {
            return parser;
        }
        switch (propertyName) {
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
