package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.toplevel.*;

public class TopLevelPropertyParserFactory {

    //    TODO: Spring beans?
//    private CommonPropertyParserFactory commonFactory = new CommonPropertyParserFactory();
//    private InheritedPropertyParserFactory inheritedFactory = new InheritedPropertyParserFactory();

    public static <T extends TopLevelDescription> PropertyParser<T> createParser(String key) {
        PropertyParser<T> commonParser = CommonPropertyParserFactory.createParser(key);
        if (commonParser != null) {
            return commonParser;
        }
        PropertyParser<T> inheritedParser = InheritedPropertyParserFactory.createParser(key);
        if (inheritedParser != null) {
            return inheritedParser;
        }
        switch (key) {
            case CsvwKeywords.CONTEXT_PROPERTY:
                return new ContextPropertyParser<>();
            case CsvwKeywords.DIALECT_PROPERTY:
                return new DialectPropertyParser<>();
            case CsvwKeywords.NOTES_PROPERTY:
                return new NotesPropertyParser<>();
            case CsvwKeywords.TABLE_DIRECTION_PROPERTY:
                return new TableDirectionPropertyParser<>();
            case CsvwKeywords.TABLE_SCHEMA_PROPERTY:
                return new TableSchemaPropertyParser<>();
            case CsvwKeywords.TRANSFORMATIONS_PROPERTY:
                return new TransformationsPropertyParser<>();
            default:
                return null;
        }
    }

}
