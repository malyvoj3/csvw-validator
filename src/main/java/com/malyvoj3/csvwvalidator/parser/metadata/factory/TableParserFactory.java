package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DialectDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.table.SuppressOutputPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.table.UrlPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class TableParserFactory<T extends TableDescription> extends TopLevelParserFactory<T> {

    public TableParserFactory(SchemaDescriptionParser schemaDescriptionParser,
                              DialectDescriptionParser dialectDescriptionParser) {
        super(schemaDescriptionParser, dialectDescriptionParser);
    }

    @Override
    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> topLevelParser = super.createParser(propertyName);
        if (topLevelParser != null) {
            return topLevelParser;
        }
        switch (propertyName) {
            case CsvwKeywords.URL_PROPERTY:
                return new UrlPropertyParser<>();
            case CsvwKeywords.SUPPRESS_OUTPUT_PROPERTY:
                return new SuppressOutputPropertyParser<>();
            default:
                return null;
        }
    }

}
