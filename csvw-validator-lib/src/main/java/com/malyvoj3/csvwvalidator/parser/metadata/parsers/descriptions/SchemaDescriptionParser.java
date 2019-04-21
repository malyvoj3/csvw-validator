package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchemaDescriptionParser extends DefaultDescriptionParser<SchemaDescription> {

    public SchemaDescriptionParser(ParserFactory<SchemaDescription> factory) {
        super(factory);
    }

    @Override
    protected SchemaDescription createNewDescription() {
        return new SchemaDescription();
    }
}
