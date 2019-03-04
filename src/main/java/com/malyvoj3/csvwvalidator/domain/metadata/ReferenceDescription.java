package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReferenceDescription extends ObjectDescription {

    private LinkProperty resource;
    private LinkProperty schemaReference;
    private ColumnReferenceProperty columnReference;

    @Override
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(resource, context);
        normalizeProperty(schemaReference, context);
        normalizeProperty(columnReference, context);
    }

}
