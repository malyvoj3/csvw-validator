package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.ReferenceDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.ReferencePropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReferenceDescriptionParser {

    // TODO string bean?
    private ReferencePropertyParserFactory factory = new ReferencePropertyParserFactory();

    public ReferenceDescription parse(ObjectNode objectNode) {
        ReferenceDescription referenceDescription = new ReferenceDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<ReferenceDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(referenceDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property");
            }
        });
        return referenceDescription;
    }

}
