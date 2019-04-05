package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class InheritanceDescription extends CommonDescription {

    protected UriTemplateProperty aboutUrl;
    protected AtomicProperty<DataTypeDescription> dataType;
    protected StringAtomicProperty defaultValue;
    protected StringAtomicProperty lang;
    protected ListAtomicProperty<String> nullValue;
    protected BooleanAtomicProperty ordered;
    protected UriTemplateProperty propertyUrl;
    protected BooleanAtomicProperty required;
    protected StringAtomicProperty separator;
    protected StringAtomicProperty textDirection;
    protected UriTemplateProperty valueUrl;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(aboutUrl, context));
        normalizationErrors.addAll(normalizeProperty(dataType, context));
        normalizationErrors.addAll(normalizeProperty(defaultValue, context));
        normalizationErrors.addAll(normalizeProperty(lang, context));
        normalizationErrors.addAll(normalizeProperty(nullValue, context));
        normalizationErrors.addAll(normalizeProperty(ordered, context));
        normalizationErrors.addAll(normalizeProperty(propertyUrl, context));
        normalizationErrors.addAll(normalizeProperty(required, context));
        normalizationErrors.addAll(normalizeProperty(separator, context));
        normalizationErrors.addAll(normalizeProperty(textDirection, context));
        normalizationErrors.addAll(normalizeProperty(valueUrl, context));
        return normalizationErrors;
    }
}
