package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.tablegroup.TableDirectionPropertyParser;

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
            case "tableDirection":
                return new TableDirectionPropertyParser<>();
            default:
                return null;
        }
    }


}
