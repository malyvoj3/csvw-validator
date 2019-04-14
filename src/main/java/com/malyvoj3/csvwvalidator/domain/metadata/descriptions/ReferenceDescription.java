package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReferenceDescription extends ObjectDescription {

    private LinkProperty resource;
    private LinkProperty schemaReference;
    private ColumnReferenceProperty columnReference;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(resource, context));
        normalizationErrors.addAll(normalizeProperty(schemaReference, context));
        normalizationErrors.addAll(normalizeProperty(columnReference, context));
        return normalizationErrors;
    }

}
