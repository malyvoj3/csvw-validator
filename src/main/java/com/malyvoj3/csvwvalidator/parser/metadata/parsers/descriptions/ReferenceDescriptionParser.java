package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ReferenceDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReferenceDescriptionParser extends StrictDescriptionParser<ReferenceDescription> {

    public ReferenceDescriptionParser(ParserFactory<ReferenceDescription> factory) {
        super(factory);
    }

    @Override
    protected ReferenceDescription createNewDescription() {
        return new ReferenceDescription();
    }
}
