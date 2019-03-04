package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

import lombok.Data;

@Data
public abstract class ObjectDescription implements Normalizable {

    private LinkProperty id;
    private StringAtomicProperty type;

    @Override
    public void normalize(Context context) {
        normalizeProperty(id, context);
    }

    protected void normalizeProperty(Property property, Context context) {
        if (property != null) {
            property.normalize(context);
        }
    }
}
