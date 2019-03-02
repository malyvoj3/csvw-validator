package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.ColumnReferencePropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.ReferencePropertyParser;

public class ForeignKeyPropertyParserFactory {

    public static PropertyParser<ForeignKeyDescription> createParser(String key) {
        switch (key) {
            case CsvwKeywords.COLUMN_REFERENCE_PROPERTY:
                return new ColumnReferencePropertyParser<>();
            case CsvwKeywords.RESOURCE_PROPERTY:
                return new ReferencePropertyParser<>();
            default:
                return null;
        }
    }

}
