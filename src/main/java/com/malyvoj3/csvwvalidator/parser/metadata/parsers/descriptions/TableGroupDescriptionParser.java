package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableGroupDescriptionParser extends DefaultDescriptionParser<TableGroupDescription> {

    public TableGroupDescriptionParser(ParserFactory<TableGroupDescription> factory) {
        super(factory);
    }

    @Override
    protected TableGroupDescription createNewDescription() {
        return new TableGroupDescription();
    }
}
