package com.malyvoj3.csvwvalidator.parser.metatada.properties.inherited;

import com.malyvoj3.csvwvalidator.domain.metadata.InheritanceDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

public class LangPropertyParser<T extends InheritanceDescription> implements PropertyParser<T> {

    @Override
    public T parseProperty(T description) {
        return null;
    }
}
