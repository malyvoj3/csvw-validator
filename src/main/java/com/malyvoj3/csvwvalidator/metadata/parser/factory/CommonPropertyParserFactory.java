package com.malyvoj3.csvwvalidator.metadata.parser.factory;

import com.malyvoj3.csvwvalidator.metadata.domain.CommonDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.common.CommonPropertyParser;

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
