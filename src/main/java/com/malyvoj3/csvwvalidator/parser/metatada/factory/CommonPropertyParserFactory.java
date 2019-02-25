package com.malyvoj3.csvwvalidator.parser.metatada.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.CommonDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.common.CommonPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

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
