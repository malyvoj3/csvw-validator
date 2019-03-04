package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.SchemaDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.ColumnsPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.ForeignKeysPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.PrimaryKeyPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.RowTitlesPropertyParser;

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
            case CsvwKeywords.COLUMNS_PROPERTY:
                return new ColumnsPropertyParser<>();
            case CsvwKeywords.PRIMARY_KEY_PROPERTY:
                return new PrimaryKeyPropertyParser<>();
            case CsvwKeywords.ROW_TITLES_PROPERTY:
                return new RowTitlesPropertyParser<>();
            case CsvwKeywords.FOREIGN_KEYS_PROPERTY:
                return new ForeignKeysPropertyParser<>();
            default:
                return null;
        }
    }

}
