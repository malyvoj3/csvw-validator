package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.metadata.domain.SchemaDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.SchemaPropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchemaDescriptionParser {

    // TODO string bean?
    private SchemaPropertyParserFactory factory = new SchemaPropertyParserFactory();

    public SchemaDescription parse(ObjectNode objectNode) {
        SchemaDescription schemaDescription = new SchemaDescription();
        objectNode.fields().forEachRemaining(entry -> {
            PropertyParser<SchemaDescription> propertyParser =
                    factory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(schemaDescription, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property");
            }
        });
        return schemaDescription;
    }

}
