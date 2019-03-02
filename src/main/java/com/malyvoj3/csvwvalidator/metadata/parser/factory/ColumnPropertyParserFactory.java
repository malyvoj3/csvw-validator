package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.ColumnDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.column.NamePropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.column.SuppressOutputPropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.column.TitlesPropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.column.VirtualPropertyParser;

public class ColumnPropertyParserFactory {

    //    TODO: Spring beans?
//    private CommonPropertyParserFactory commonFactory = new CommonPropertyParserFactory();
//    private InheritedPropertyParserFactory inheritedFactory = new InheritedPropertyParserFactory();

    public static PropertyParser<ColumnDescription> createParser(String key) {
        PropertyParser<ColumnDescription> commonParser = CommonPropertyParserFactory.createParser(key);
        if (commonParser != null) {
            return commonParser;
        }
        PropertyParser<ColumnDescription> inheritedParser = InheritedPropertyParserFactory.createParser(key);
        if (inheritedParser != null) {
            return inheritedParser;
        }
        switch (key) {
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
