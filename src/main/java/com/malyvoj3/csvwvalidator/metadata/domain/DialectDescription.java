package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.*;

public class DialectDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

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
}
