package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.DialectPropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DialectDescriptionParser implements ObjectDescriptionParser<DialectDescription> {

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
