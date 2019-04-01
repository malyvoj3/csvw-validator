package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import com.malyvoj3.csvwvalidator.utils.LanguageUtils;

public class LanguageType extends TokenType {

    public LanguageType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        if (!LanguageUtils.isLanguageTag(stringValue)) {
            throw new DataTypeFormatException();
        }
    }

    public LanguageType(String stringValue, String format) throws DataTypeFormatException {
        super(stringValue, format);
        if (!LanguageUtils.isLanguageTag(stringValue)) {
            throw new DataTypeFormatException();
        }
    }

}
