package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.ForeignKeyPropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForeignKeyDescriptionParser {

    // TODO string bean?
    private ForeignKeyPropertyParserFactory factory = new ForeignKeyPropertyParserFactory();

    public ForeignKeyDescription parse(ObjectNode objectNode) {
        ForeignKeyDescription foreignKeyDescription = new ForeignKeyDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<ForeignKeyDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(foreignKeyDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return foreignKeyDescription;
    }

}
