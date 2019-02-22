package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.util.List;

public class StringAtomicProperty extends ArrayProperty<String> {

    public StringAtomicProperty(String stringValue, List<String> parsedValue) {
        super(stringValue, parsedValue);
    }

}
