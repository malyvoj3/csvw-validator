package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.util.List;

public class IntegerAtomicProperty extends ArrayProperty<Integer> {
    public IntegerAtomicProperty(String stringValue, List<Integer> parsedValue) {
        super(stringValue, parsedValue);
    }
}
