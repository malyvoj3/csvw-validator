package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.FormatDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.datatype.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataTypeParserFactory<T extends DataTypeDescription> extends CommonParserFactory<T> {

    private final FormatDescriptionParser formatDescriptionParser;

    @Override
    public PropertyParser<T> createParser(String propertyName) {
        PropertyParser<T> parser = super.createParser(propertyName);
        if (parser != null) {
            return parser;
        }
        switch (propertyName) {
            case CsvwKeywords.BASE_PROPERTY:
                return new BasePropertyParser<>();
            case CsvwKeywords.FORMAT_PROPERTY:
                return new FormatPropertyParser<>(formatDescriptionParser);
            case CsvwKeywords.LENGTH_PROPERTY:
                return new LengthPropertyParser<>();
            case CsvwKeywords.MAX_EXCLUSIVE_PROPERTY:
                return new MaxExclusivePropertyParser<>();
            case CsvwKeywords.MAXIMUM_PROPERTY:
                return new MaximumPropertyParser<>();
            case CsvwKeywords.MAX_LENGTH_PROPERTY:
                return new MaxLengthPropertyParser<>();
            case CsvwKeywords.MIN_EXCLUSIVE_PROPERTY:
                return new MinExclusivePropertyParser<>();
            case CsvwKeywords.MINIMUM_PROPERTY:
                return new MinimumPropertyParser<>();
            case CsvwKeywords.MIN_INCLUSIVE_PROPERTY:
                return new MinInclusivePropertyParser<>();
            case CsvwKeywords.MIN_LENGTH_PROPERTY:
                return new MinLengthPropertyParser<>();
            default:
                return null;
        }
    }
}
