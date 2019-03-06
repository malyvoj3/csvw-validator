package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.ReferenceDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.ColumnReferencePropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.ReferencePropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForeignKeyParserFactory<T extends ForeignKeyDescription> implements ParserFactory<T> {

    private final ReferenceDescriptionParser referenceDescriptionParser;

    public PropertyParser<T> createParser(String propertyName) {
        switch (propertyName) {
            case CsvwKeywords.COLUMN_REFERENCE_PROPERTY:
                return new ColumnReferencePropertyParser<>();
            case CsvwKeywords.REFERENCE_PROPERTY:
                return new ReferencePropertyParser<>(referenceDescriptionParser);
            default:
                return null;
        }
    }

}
