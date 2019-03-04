package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForeignKeyDescription extends ObjectDescription {

    ColumnReferenceProperty columnReference;
    ObjectProperty<ReferenceDescription> reference;

    @Override
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(columnReference, context);
        normalizeProperty(reference, context);
    }
}
