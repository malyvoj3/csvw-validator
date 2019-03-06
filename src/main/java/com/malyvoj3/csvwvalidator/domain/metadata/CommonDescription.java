package com.malyvoj3.csvwvalidator.domain.metadata;

import java.util.ArrayList;
import java.util.List;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;

public abstract class CommonDescription extends ObjectDescription {

    private List<CommonProperty> commonProperties = new ArrayList<>();

    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }

    @Override
    public void normalize(Context context) {
        super.normalize(context);
        commonProperties.forEach(commonProperty -> normalizeProperty(commonProperty, context));
    }
}
