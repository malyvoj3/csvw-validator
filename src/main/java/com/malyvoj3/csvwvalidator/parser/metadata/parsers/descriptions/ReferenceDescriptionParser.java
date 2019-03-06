package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.ReferenceDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReferenceDescriptionParser extends DefaultDescriptionParser<ReferenceDescription> {

    public ReferenceDescriptionParser(ParserFactory<ReferenceDescription> factory) {
        super(factory);
    }

    @Override
    protected ReferenceDescription createNewDescription() {
        return new ReferenceDescription();
    }
}
