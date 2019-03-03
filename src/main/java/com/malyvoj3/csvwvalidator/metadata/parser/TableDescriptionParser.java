package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.TablePropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableDescriptionParser {

    // TODO string bean?
    private TablePropertyParserFactory factory = new TablePropertyParserFactory();

    public TableDescription parse(ObjectNode objectNode) {
        TableDescription tableDescription = new TableDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<TableDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(tableDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return tableDescription;
    }

}
