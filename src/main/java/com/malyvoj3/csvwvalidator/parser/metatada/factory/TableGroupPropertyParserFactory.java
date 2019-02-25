package com.malyvoj3.csvwvalidator.parser.metatada.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.common.CommonPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited.LangPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.tablegroup.TablesPropertyParser;

public class TableGroupPropertyParserFactory {

    public static PropertyParser<TableGroupDescription> createParser(String key) {
        switch(key) {
            case "aa":
                return new TablesPropertyParser<>();
            case "bb":
                return new LangPropertyParser<>();
            case "cc":
                return new CommonPropertyParser<>();
            default:
                return null;
        }
    }


}
