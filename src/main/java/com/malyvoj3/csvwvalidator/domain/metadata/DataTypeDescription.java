package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;

import java.util.ArrayList;
import java.util.List;

public class DataTypeDescription extends CommonDescription {

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
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(numberFormat, context);
    }
}
