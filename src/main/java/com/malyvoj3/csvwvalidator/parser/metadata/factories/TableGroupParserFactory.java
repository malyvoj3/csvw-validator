package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DialectDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.tablegroup.TablesPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class TableGroupParserFactory<T extends TableGroupDescription> extends TopLevelParserFactory<T> {

    private final TableDescriptionParser tableDescriptionParser;

    public TableGroupParserFactory(SchemaDescriptionParser schemaDescriptionParser,
                                   DialectDescriptionParser dialectDescriptionParser,
                                   TableDescriptionParser tableDescriptionParser) {
        super(schemaDescriptionParser, dialectDescriptionParser);
        this.tableDescriptionParser = tableDescriptionParser;
    }

    @Override
    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> topLevelParser = super.createParser(propertyName);
        if (topLevelParser != null) {
            return topLevelParser;
        }
        switch (propertyName) {
            case CsvwKeywords.TABLES_PROPERTY:
                return new TablesPropertyParser<>(tableDescriptionParser);
            default:
                return null;
        }
    }


}
