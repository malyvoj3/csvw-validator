package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.SchemaDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.tablegroup.TablesPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class TableGroupParserFactory<T extends TableGroupDescription> extends TopLevelParserFactory<T> {

    private final TableDescriptionParser tableDescriptionParser;

    public TableGroupParserFactory(SchemaDescriptionParser schemaDescriptionParser, TableDescriptionParser tableDescriptionParser) {
        super(schemaDescriptionParser);
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
