package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.ColumnReferencePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.ReferencePropertyParser;

public class ForeignKeyPropertyParserFactory {

    public static PropertyParser<ForeignKeyDescription> createParser(String key) {
        switch (key) {
            case CsvwKeywords.COLUMN_REFERENCE_PROPERTY:
                return new ColumnReferencePropertyParser<>();
            case CsvwKeywords.REFERENCE_PROPERTY:
                return new ReferencePropertyParser<>();
            default:
                return null;
        }
    }

}
