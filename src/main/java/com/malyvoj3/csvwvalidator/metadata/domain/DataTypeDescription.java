package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.*;

import java.util.ArrayList;
import java.util.List;

public class DataTypeDescription implements CommonDescription {

    private List<CommonProperty> commonProperties = new ArrayList<>();

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private StringAtomicProperty base;
    // Format je string a nebo numberFormat.
    private StringAtomicProperty format;
    private ObjectProperty<NumberFormatDescription> numberFormat;

    private IntegerAtomicProperty length;
    private IntegerAtomicProperty minLength;
    private IntegerAtomicProperty maxLength;

    // Muze byt string/cislo, zatim necham jako string a uvidime jak to vyresit.
    private StringAtomicProperty minimum;
    private StringAtomicProperty minInclusive;
    private StringAtomicProperty minExclusive;
    private StringAtomicProperty maximum;
    private StringAtomicProperty maxInclusive;
    private StringAtomicProperty maxExclusive;

    @Override
    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }
}
