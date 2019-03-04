package com.malyvoj3.csvwvalidator.parser.metadata.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.common.CommonPropertyParser;

public class CommonPropertyParserFactory {

    public static <T extends CommonDescription> PropertyParser<T> createParser(String key) {
        switch(key) {
            case "aa":
                return new CommonPropertyParser<>();
            default:
                return null;
        }
    }

}
