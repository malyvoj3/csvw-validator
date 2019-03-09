package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public abstract class ObjectDescription implements Normalizable {

    private LinkProperty id;
    private StringAtomicProperty type;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = new ArrayList<>();
        normalizationErrors.addAll(normalizeProperty(id, context));
        return normalizationErrors;
    }

    protected List<ValidationError> normalizeProperty(Property<?> property, Context context) {
        List<ValidationError> normalizationErrors;
        if (property != null) {
            normalizationErrors = property.normalize(context);
        } else {
            normalizationErrors = Collections.emptyList();
        }
        return normalizationErrors;
    }
}
