package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForeignKeyDescription extends ObjectDescription {

    ColumnReferenceProperty columnReference;
    ObjectProperty<ReferenceDescription> reference;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(columnReference, context));
        normalizationErrors.addAll(normalizeProperty(reference, context));
        return normalizationErrors;
    }
}
