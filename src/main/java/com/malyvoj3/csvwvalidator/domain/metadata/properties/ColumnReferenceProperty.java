package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnReferenceProperty extends Property<List<String>> {

    public ColumnReferenceProperty(List<String> value) {
        super(value);
    }

}
