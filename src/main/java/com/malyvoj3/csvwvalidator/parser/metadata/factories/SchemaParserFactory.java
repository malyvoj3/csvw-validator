package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.ColumnDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.ForeignKeyDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.ColumnsPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.ForeignKeysPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.PrimaryKeyPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.schema.RowTitlesPropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchemaParserFactory<T extends SchemaDescription> extends InheritedParserFactory<T> {

    private final ColumnDescriptionParser columnDescriptionParser;
    private final ForeignKeyDescriptionParser foreignKeyDescriptionParser;

    @Override
    public PropertyParser<T> createParser(String key) {
        PropertyParser<T> inheritedParser = super.createParser(key);
        if (inheritedParser != null) {
            return inheritedParser;
        }
        switch (key) {
            case CsvwKeywords.COLUMNS_PROPERTY:
                return new ColumnsPropertyParser<>(columnDescriptionParser);
            case CsvwKeywords.PRIMARY_KEY_PROPERTY:
                return new PrimaryKeyPropertyParser<>();
            case CsvwKeywords.ROW_TITLES_PROPERTY:
                return new RowTitlesPropertyParser<>();
            case CsvwKeywords.FOREIGN_KEYS_PROPERTY:
                return new ForeignKeysPropertyParser<>(foreignKeyDescriptionParser);
            default:
                return null;
        }
    }

}
