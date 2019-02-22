package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

public class DataTypeDescription {

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
}
