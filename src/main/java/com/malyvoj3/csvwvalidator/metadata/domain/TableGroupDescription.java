package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableGroupDescription extends TopLevelDescription implements CommonDescription {

        // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private ArrayProperty<TableDescription> tables;
}
