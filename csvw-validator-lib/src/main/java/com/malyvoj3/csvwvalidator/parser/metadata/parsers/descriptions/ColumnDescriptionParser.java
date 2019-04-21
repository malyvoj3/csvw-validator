package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColumnDescriptionParser extends DefaultDescriptionParser<ColumnDescription> {

    public ColumnDescriptionParser(ParserFactory<ColumnDescription> factory) {
        super(factory);
    }

    @Override
    protected ColumnDescription createNewDescription() {
        return new ColumnDescription();
    }
}
