package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableDescription extends TopLevelDescription implements CommonDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private LinkProperty url;
    private BooleanAtomicProperty suppressOutput;
}
