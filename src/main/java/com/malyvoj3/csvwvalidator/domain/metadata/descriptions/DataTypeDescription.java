package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.validation.ValidationError;

import java.util.List;

public class DataTypeDescription extends CommonDescription {

    private StringAtomicProperty base;
    private StringAtomicProperty format;
    private ObjectProperty<NumberFormatDescription> numberFormat;

    private IntegerAtomicProperty length;
    private IntegerAtomicProperty minLength;
    private IntegerAtomicProperty maxLength;

    // Saved as strings.
    private StringAtomicProperty minimum;
    private StringAtomicProperty minInclusive;
    private StringAtomicProperty minExclusive;
    private StringAtomicProperty maximum;
    private StringAtomicProperty maxInclusive;
    private StringAtomicProperty maxExclusive;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(numberFormat, context));
        return normalizationErrors;
    }
}
