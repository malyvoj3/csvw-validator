package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;

public class TableDescription extends TopLevelDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private LinkProperty url;

    private ObjectProperty<DialectDescription> dialect;

    private ArrayProperty<CommonProperty> notes;

    private BooleanAtomicProperty suppressOutput;

    private StringAtomicProperty tableDirection;

    private ObjectProperty<SchemaDescription> tableSchema;

    private ArrayProperty<TransformationDescription> transformations;

}
