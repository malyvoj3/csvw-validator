package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableDescriptionParser extends DefaultDescriptionParser<TableDescription> {

    public TableDescriptionParser(ParserFactory<TableDescription> factory) {
        super(factory);
    }

    @Override
    protected TableDescription createNewDescription() {
        return new TableDescription();
    }
}
