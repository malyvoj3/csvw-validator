package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ForeignKeyDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForeignKeyDescriptionParser extends StrictDescriptionParser<ForeignKeyDescription> {

    public ForeignKeyDescriptionParser(ParserFactory<ForeignKeyDescription> factory) {
        super(factory);
    }

    @Override
    protected ForeignKeyDescription createNewDescription() {
        return new ForeignKeyDescription();
    }
}
