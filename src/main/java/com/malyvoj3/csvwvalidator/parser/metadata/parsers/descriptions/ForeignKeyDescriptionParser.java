package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForeignKeyDescriptionParser extends DefaultDescriptionParser<ForeignKeyDescription> {

    public ForeignKeyDescriptionParser(ParserFactory<ForeignKeyDescription> factory) {
        super(factory);
    }

    @Override
    protected ForeignKeyDescription createNewDescription() {
        return new ForeignKeyDescription();
    }
}
