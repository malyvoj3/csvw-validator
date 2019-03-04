package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.TableGroupPropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableGroupDescriptionParser implements ObjectDescriptionParser<TableGroupDescription> {

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
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return tableGroupDescription;
    }

}
