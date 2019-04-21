package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;

public class FormatDescriptionParser extends DefaultDescriptionParser<FormatDescription> {

    public FormatDescriptionParser(ParserFactory<FormatDescription> factory) {
        super(factory);
    }

    @Override
    protected FormatDescription createNewDescription() {
        return new FormatDescription();
    }
}
