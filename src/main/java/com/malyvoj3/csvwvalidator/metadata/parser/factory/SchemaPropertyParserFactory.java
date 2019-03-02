package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.metadata.domain.SchemaDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.ColumnsPropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.ForeignKeysPropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.PrimaryKeyPropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.RowTitlesPropertyParser;

public class SchemaPropertyParserFactory {

//    TODO: Spring beans?
//    private CommonPropertyParserFactory commonFactory = new CommonPropertyParserFactory();
//    private InheritedPropertyParserFactory inheritedFactory = new InheritedPropertyParserFactory();

    public static PropertyParser<SchemaDescription> createParser(String key) {
        PropertyParser<SchemaDescription> commonParser = CommonPropertyParserFactory.createParser(key);
        if (commonParser != null) {
            return commonParser;
        }
        PropertyParser<SchemaDescription> inheritedParser = InheritedPropertyParserFactory.createParser(key);
        if (inheritedParser != null) {
            return inheritedParser;
        }
        switch (key) {
            case "columns":
                return new ColumnsPropertyParser<>();
            case "primaryKey":
                return new PrimaryKeyPropertyParser<>();
            case "rowTitles":
                return new RowTitlesPropertyParser<>();
            case "foreignKeys":
                return new ForeignKeysPropertyParser<>();
            default:
                return null;
        }
    }

}
