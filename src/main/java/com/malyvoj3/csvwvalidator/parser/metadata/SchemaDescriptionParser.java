package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.SchemaDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.SchemaPropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchemaDescriptionParser implements ObjectDescriptionParser<SchemaDescription> {

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
                log.warn("Unknown property {}.", entry.getKey());
            }
        });
        return schemaDescription;
    }

}
