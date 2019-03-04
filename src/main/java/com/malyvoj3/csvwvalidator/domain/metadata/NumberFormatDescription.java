package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NumberFormatDescription extends ObjectDescription {

    private StringAtomicProperty decimalChar;
    private StringAtomicProperty groupChar;
    private StringAtomicProperty pattern;

    @Override
    public void normalize(Context context) {
        super.normalize(context);
    }
}
