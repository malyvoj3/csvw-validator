package com.malyvoj3.csvwvalidator.parser.metatada.factory;

import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited.LangPropertyParser;

public class InheritedPropertyParserFactory {

    public static <T extends InheritanceDescription> PropertyParser<T> createParser(String key) {
        switch(key) {
            case "aa":
                return new LangPropertyParser<>();
            default:
                return null;
        }
    }

}
