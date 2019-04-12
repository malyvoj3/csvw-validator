package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.AtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataTypeDescription extends CommonDescription {

    private StringAtomicProperty base;
    private AtomicProperty<FormatDescription> format;

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
    public String getValidType() {
        return "Datatype";
    }
}
