package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DialectDescriptionParser extends DefaultDescriptionParser<DialectDescription> {

    public DialectDescriptionParser(ParserFactory<DialectDescription> factory) {
        super(factory);
    }

    @Override
    protected DialectDescription createNewDescription() {
        return new DialectDescription();
    }
}
