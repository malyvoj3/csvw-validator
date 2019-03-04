package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class InheritanceDescription extends ObjectDescription {

    protected UriTemplateProperty aboutUrl;
    // Muze byt i jen string.
    protected ObjectProperty<DataTypeDescription> dataType;
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
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(aboutUrl, context);
        normalizeProperty(dataType, context);
        normalizeProperty(defaultValue, context);
        normalizeProperty(lang, context);
        normalizeProperty(nullValue, context);
        normalizeProperty(ordered, context);
        normalizeProperty(propertyUrl, context);
        normalizeProperty(required, context);
        normalizeProperty(separator, context);
        normalizeProperty(textDirection, context);
        normalizeProperty(valueUrl, context);
    }
}
