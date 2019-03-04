package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DialectDescription extends ObjectDescription {

    private StringAtomicProperty commentPrefix;

    private StringAtomicProperty delimiter;

    private BooleanAtomicProperty doubleQuote;

    private StringAtomicProperty encoding;

    private BooleanAtomicProperty header;

    private IntegerAtomicProperty headerRowCount;

    private ListAtomicProperty<String> lineTerminators;

    private StringAtomicProperty quoteChar;

    private BooleanAtomicProperty skipBlankRows;

    private IntegerAtomicProperty skipColumns;

    private BooleanAtomicProperty skipInitialSpace;

    private IntegerAtomicProperty skipRows;

    private StringAtomicProperty trim;

    @Override
    public void normalize(Context context) {
        super.normalize(context);
    }
}
