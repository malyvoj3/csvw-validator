package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;

public class ForeignKeyDescription {

    ColumnReferenceProperty columnReference;
    ObjectProperty<ReferenceDescription> reference;

}
