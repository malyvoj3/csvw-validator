package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DataTypeDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DialectDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.toplevel.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class TopLevelParserFactory<T extends TopLevelDescription> extends InheritedParserFactory<T> {

    private final SchemaDescriptionParser schemaDescriptionParser;
    private final DialectDescriptionParser dialectDescriptionParser;

    public TopLevelParserFactory(DataTypeDescriptionParser dataTypeDescriptionParser,
                                 SchemaDescriptionParser schemaDescriptionParser,
                                 DialectDescriptionParser dialectDescriptionParser) {
        super(dataTypeDescriptionParser);
        this.schemaDescriptionParser = schemaDescriptionParser;
        this.dialectDescriptionParser = dialectDescriptionParser;
    }

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
                return new DialectPropertyParser<>(dialectDescriptionParser);
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
