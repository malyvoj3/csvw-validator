package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ObjectProperty;
import lombok.Data;

@Data
public class ForeignKeyDescription {

    ColumnReferenceProperty columnReference;
    ObjectProperty<ReferenceDescription> reference;

}
