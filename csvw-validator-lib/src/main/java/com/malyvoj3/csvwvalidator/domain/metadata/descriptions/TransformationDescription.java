package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

import java.util.List;

public class TransformationDescription extends ObjectDescription {

    //required
    private LinkProperty url;
    private LinkProperty scriptFormat;
    private LinkProperty targetFormat;

    //optional
    private StringAtomicProperty source;
    private NaturalLanguageProperty titles;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(url, context));
        normalizationErrors.addAll(normalizeProperty(scriptFormat, context));
        normalizationErrors.addAll(normalizeProperty(targetFormat, context));
        normalizationErrors.addAll(normalizeProperty(source, context));
        normalizationErrors.addAll(normalizeProperty(titles, context));
        return normalizationErrors;
    }

    @Override
    public String getValidType() {
        return "Template";
    }
}
