package com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;

public class DataTypeDescriptionParser extends DefaultDescriptionParser<DataTypeDescription> {

    public DataTypeDescriptionParser(ParserFactory<DataTypeDescription> factory) {
        super(factory);
    }

    @Override
    protected DataTypeDescription createNewDescription() {
        return new DataTypeDescription();
    }
}
