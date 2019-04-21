package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.IntegerAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ListAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
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
    public String getValidType() {
        return "Dialect";
    }
}
