package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ColumnDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.ColumnPropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColumnDescriptionParser {

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
