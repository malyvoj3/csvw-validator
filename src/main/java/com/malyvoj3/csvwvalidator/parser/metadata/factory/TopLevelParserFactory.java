package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.toplevel.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TopLevelParserFactory<T extends TopLevelDescription> extends InheritedParserFactory<T> {

    private final SchemaDescriptionParser schemaDescriptionParser;

    @Override
    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> parser = super.createParser(propertyName);
        if (parser != null) {
            return parser;
        }
        switch (propertyName) {
            case CsvwKeywords.CONTEXT_PROPERTY:
                return new ContextPropertyParser<>();
            case CsvwKeywords.DIALECT_PROPERTY:
                return new DialectPropertyParser<>(null);
            case CsvwKeywords.NOTES_PROPERTY:
                return new NotesPropertyParser<>();
            case CsvwKeywords.TABLE_DIRECTION_PROPERTY:
                return new TableDirectionPropertyParser<>();
            case CsvwKeywords.TABLE_SCHEMA_PROPERTY:
                return new TableSchemaPropertyParser<>(schemaDescriptionParser);
            case CsvwKeywords.TRANSFORMATIONS_PROPERTY:
                return new TransformationsPropertyParser<>();
            default:
                return null;
        }
    }

}
