package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;

public class TableGroupDescription extends TopLevelDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;


    private ArrayProperty<TableDescription> tables;

    private ObjectProperty<DialectDescription> dialect;

    private ArrayProperty<CommonProperty> notes;

    private StringAtomicProperty tableDirection;

    private ObjectProperty<SchemaDescription> tableSchema;

    private ArrayProperty<TransformationDescription> transformations;

}
