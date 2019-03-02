package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.TableGroupPropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableGroupDescriptionParser {

    // TODO string bean?
    private TableGroupPropertyParserFactory factory = new TableGroupPropertyParserFactory();

    public TableGroupDescription parse(ObjectNode objectNode) {
        TableGroupDescription tableGroupDescription = new TableGroupDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<TableGroupDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(tableGroupDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property");
            }
        });
        return tableGroupDescription;
    }

}
