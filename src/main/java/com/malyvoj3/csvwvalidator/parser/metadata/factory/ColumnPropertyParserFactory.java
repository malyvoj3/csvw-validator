package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.ColumnDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.column.NamePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.column.SuppressOutputPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.column.TitlesPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.column.VirtualPropertyParser;

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
