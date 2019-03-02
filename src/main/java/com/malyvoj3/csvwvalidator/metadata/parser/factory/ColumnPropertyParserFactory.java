package com.malyvoj3.csvwvalidator.metadata.parser.factory;

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
            case "name":
                return new NamePropertyParser<>();
            case "suppressOutput":
                return new SuppressOutputPropertyParser<>();
            case "titles":
                return new TitlesPropertyParser<>();
            case "virtual":
                return new VirtualPropertyParser<>();
            default:
                return null;
        }
    }

}
