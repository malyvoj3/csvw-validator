package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.tablegroup.TablesPropertyParser;

public class TableGroupPropertyParserFactory {

//    TODO: Spring beans?
//    private TopLevelPropertyParserFactory topLeveFactory = new TopLevelPropertyParserFactory();

    public static PropertyParser<TableGroupDescription> createParser(String key) {
        PropertyParser<TableGroupDescription> topLevelParser = TopLevelPropertyParserFactory.createParser(key);
        if (topLevelParser != null) {
            return topLevelParser;
        }
        switch (key) {
            case CsvwKeywords.TABLES_PROPERTY:
                return new TablesPropertyParser<>();
            default:
                return null;
        }
    }


}
