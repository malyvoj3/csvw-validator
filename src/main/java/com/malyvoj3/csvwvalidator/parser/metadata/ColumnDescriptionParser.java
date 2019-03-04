package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ColumnDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.ColumnPropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColumnDescriptionParser implements ObjectDescriptionParser<ColumnDescription> {

    // TODO string bean?
    private ColumnPropertyParserFactory factory = new ColumnPropertyParserFactory();

    public ColumnDescription parse(ObjectNode objectNode) {
        ColumnDescription columnDescription = new ColumnDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<ColumnDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(columnDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return columnDescription;
    }

}
