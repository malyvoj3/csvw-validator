package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import lombok.Data;

@Data
public class ReferenceDescription {

    private LinkProperty resource;
    private LinkProperty schemaReference;
    private ColumnReferenceProperty columnReference;

}
