package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.DialectPropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DialectDescriptionParser {

    // TODO string bean?
    private DialectPropertyParserFactory factory = new DialectPropertyParserFactory();

    public DialectDescription parse(ObjectNode objectNode) {
        DialectDescription dialectDescription = new DialectDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<DialectDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(dialectDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return dialectDescription;
    }

}
