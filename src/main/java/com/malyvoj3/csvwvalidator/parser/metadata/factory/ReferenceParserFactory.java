package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.ReferenceDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.ReferenceColumnReferencePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.ResourcePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.SchemaReferencePropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class ReferenceParserFactory<T extends ReferenceDescription> implements ParserFactory<T> {

    public PropertyParser<T> createParser(String propertyName) {
        switch (propertyName) {
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
