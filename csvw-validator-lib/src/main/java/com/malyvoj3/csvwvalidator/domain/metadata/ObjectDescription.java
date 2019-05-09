package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Object description from CSV on the Web - JSON-LD document.
 */
@Data
public abstract class ObjectDescription implements Normalizable {

    private LinkProperty id;
    private StringAtomicProperty type;

    @Override
    public List<ValidationError> normalize(Context context) {
        return new ArrayList<>(normalizeProperty(id, context));
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

    public String getValidType() {
        return null;
    }
}
