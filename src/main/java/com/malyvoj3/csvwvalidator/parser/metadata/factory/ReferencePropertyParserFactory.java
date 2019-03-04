package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.ReferenceDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.ReferenceColumnReferencePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.ResourcePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.schema.SchemaReferencePropertyParser;

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
