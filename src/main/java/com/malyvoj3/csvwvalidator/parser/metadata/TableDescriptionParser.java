package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.TablePropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableDescriptionParser implements ObjectDescriptionParser<TableDescription> {

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
