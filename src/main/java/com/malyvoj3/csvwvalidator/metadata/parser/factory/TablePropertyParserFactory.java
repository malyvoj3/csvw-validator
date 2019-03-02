package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.TableDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.table.SuppressOutputPropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.table.UrlPropertyParser;

public class TablePropertyParserFactory {

//    TODO: Spring beans?
//    private TopLevelPropertyParserFactory topLeveFactory = new TopLevelPropertyParserFactory();

    public static PropertyParser<TableDescription> createParser(String key) {
        PropertyParser<TableDescription> topLevelParser = TopLevelPropertyParserFactory.createParser(key);
        if (topLevelParser != null) {
            return topLevelParser;
        }
        switch (key) {
            case CsvwKeywords.URL_PROPERTY:
                return new UrlPropertyParser<>();
            case CsvwKeywords.SUPPRESS_OUTPUT_PROPERTY:
                return new SuppressOutputPropertyParser<>();
            default:
                return null;
        }
    }

}
