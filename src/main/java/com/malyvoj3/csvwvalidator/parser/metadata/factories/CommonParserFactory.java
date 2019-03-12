package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.common.CommonPropertyParser;
import com.malyvoj3.csvwvalidator.utils.UriUtils;

public class CommonParserFactory<T extends CommonDescription> implements ParserFactory<T> {

    public PropertyParser<T> createParser(String propertyName) {
        if (UriUtils.isCommonProperty(propertyName)) {
            return new CommonPropertyParser<>();
        } else {
            return null;
        }
    }

}
