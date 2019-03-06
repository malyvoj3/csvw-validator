package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.common.CommonPropertyParser;

public class CommonParserFactory<T extends CommonDescription> implements ParserFactory<T> {

    public PropertyParser<T> createParser(String propertyName) {
        switch (propertyName) {
            case "aa":
                return new CommonPropertyParser<>();
            default:
                return null;
        }
    }

}
