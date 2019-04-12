package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.common.CommonPropertyParser;
import com.malyvoj3.csvwvalidator.utils.UriUtils;

public class CommonParserFactory<T extends CommonDescription> extends ObjectDescriptionParserFactory<T> {

    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> propertyParser = super.createParser(propertyName);
        if (propertyParser != null) {
            return propertyParser;
        }
        if (UriUtils.isCommonProperty(propertyName)) {
            return new CommonPropertyParser<>();
        } else {
            return null;
        }
    }

}
