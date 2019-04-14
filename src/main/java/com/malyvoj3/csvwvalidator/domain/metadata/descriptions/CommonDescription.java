package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonDescription extends ObjectDescription {

    private List<CommonProperty> commonProperties = new ArrayList<>();

    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        commonProperties.forEach(commonProperty -> normalizationErrors.addAll(normalizeProperty(commonProperty, context)));
        return normalizationErrors;
    }
}
