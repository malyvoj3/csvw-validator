package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

public class TransformationDescription extends ObjectDescription {

    //required
    private LinkProperty url;
    private LinkProperty scriptFormat;
    private LinkProperty targetFormat;

    //optional
    private StringAtomicProperty source;
    private NaturalLanguageProperty titles;

    @Override
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(url, context);
        normalizeProperty(scriptFormat, context);
        normalizeProperty(targetFormat, context);
        normalizeProperty(source, context);
        normalizeProperty(titles, context);
    }
}
