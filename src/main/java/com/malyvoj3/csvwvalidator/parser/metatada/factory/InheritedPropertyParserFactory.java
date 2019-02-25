package com.malyvoj3.csvwvalidator.parser.metatada.factory;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited.*;

public class InheritedPropertyParserFactory {

    public static <T extends InheritanceDescription> PropertyParser<T> createParser(String key) {
        switch (key) {
            case CsvwKeywords.ABOUT_URL_PROPERTY:
                return new AboutUrlPropertyParser<>();
            case CsvwKeywords.DATATYPE_PROPERTY:
                return new DatatypePropertyParser<>();
            case CsvwKeywords.DEFAULT_PROPERTY:
                return new DefaultPropertyParser<>();
            case CsvwKeywords.LANG_PROPERTY:
                return new LangPropertyParser<>();
            case CsvwKeywords.NULL_PROPERTY:
                return new NullPropertyParser<>();
            case CsvwKeywords.ORDERED_PROPERTY:
                return new OrderedPropertyParser<>();
            case CsvwKeywords.PROPERTY_URL_PROPERTY:
                return new PropertyUrlPropertyParser<>();
            case CsvwKeywords.REQUIRED_PROPERTY:
                return new RequiredPropertyParser<>();
            case CsvwKeywords.SEPARATOR_PROPERTY:
                return new SeparatorPropertyParser<>();
            case CsvwKeywords.TEXT_DIRECTION_PROPERTY:
                return new TextDirectionPropertyParser<>();
            case CsvwKeywords.VALUE_URL_PROPERTY:
                return new ValueUrlPropertyParser<>();
            default:
                return null;
        }
    }

}
