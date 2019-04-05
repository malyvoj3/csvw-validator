package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.DataTypeDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.inherited.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InheritedParserFactory<T extends InheritanceDescription> extends CommonParserFactory<T> {

    private final DataTypeDescriptionParser dataTypeDescriptionParser;

    @Override
    public PropertyParser<T> createParser(String key) {
        PropertyParser<T> propertyParser = super.createParser(key);
        if (propertyParser != null) {
            return propertyParser;
        }
        switch (key) {
            case CsvwKeywords.ABOUT_URL_PROPERTY:
                return new AboutUrlPropertyParser<>();
            case CsvwKeywords.DATATYPE_PROPERTY:
                return new DataTypePropertyParser<>(dataTypeDescriptionParser);
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
