package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.ReferenceDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.ReferenceColumnReferencePropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.ResourcePropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.schema.SchemaReferencePropertyParser;

public class ReferencePropertyParserFactory {

    public static PropertyParser<ReferenceDescription> createParser(String key) {
        switch (key) {
            case CsvwKeywords.RESOURCE_PROPERTY:
                return new ResourcePropertyParser<>();
            case CsvwKeywords.SCHEMA_REFERENCE_PROPERTY:
                return new SchemaReferencePropertyParser<>();
            case CsvwKeywords.COLUMN_REFERENCE_PROPERTY:
                return new ReferenceColumnReferencePropertyParser<>();
            default:
                return null;
        }
    }

}
