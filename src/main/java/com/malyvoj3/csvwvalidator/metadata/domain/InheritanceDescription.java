package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.*;
import lombok.Data;

@Data
public abstract class InheritanceDescription {

    // TODO defaultni hodnoty?

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

}
