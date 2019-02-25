package com.malyvoj3.csvwvalidator.parser.metatada.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.tablegroup.TablesPropertyParser;

public class TableGroupPropertyParserFactory {

    public static PropertyParser<TableGroupDescription> createParser(String key) {
        PropertyParser<TableGroupDescription> commonParser = CommonPropertyParserFactory.createParser(key);
        if (commonParser != null) {
            return commonParser;
        }
        PropertyParser<TableGroupDescription> inheritedParser = InheritedPropertyParserFactory.createParser(key);
        if (inheritedParser != null) {
            return inheritedParser;
        }
        switch (key) {
            case "aa":
                return new TablesPropertyParser<>();
            default:
                return null;
        }
    }


}
