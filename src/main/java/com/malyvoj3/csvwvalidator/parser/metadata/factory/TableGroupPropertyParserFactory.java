package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.tablegroup.TablesPropertyParser;

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
