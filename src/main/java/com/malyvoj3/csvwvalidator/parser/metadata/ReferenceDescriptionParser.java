package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ReferenceDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.ReferencePropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReferenceDescriptionParser implements ObjectDescriptionParser<ReferenceDescription> {

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
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return referenceDescription;
    }

}
