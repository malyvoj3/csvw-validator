package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

public class SchemaDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private ArrayProperty<ColumnDescription> columns;

    private ColumnReferenceProperty privateKey;

    private ColumnReferenceProperty rowTitles;

    private ArrayProperty<ForeignKeyDescription> foreignKeys;


}
