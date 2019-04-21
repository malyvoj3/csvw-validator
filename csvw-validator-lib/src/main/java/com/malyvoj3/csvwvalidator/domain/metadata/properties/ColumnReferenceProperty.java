package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnReferenceProperty extends Property<List<String>> {

    public ColumnReferenceProperty(List<String> value) {
        super(value);
    }

}
