package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

public class ColumnDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private StringAtomicProperty name;

    private BooleanAtomicProperty suppressOutput;

    private NaturalLanguageProperty titles;

    private BooleanAtomicProperty virtual;

}
