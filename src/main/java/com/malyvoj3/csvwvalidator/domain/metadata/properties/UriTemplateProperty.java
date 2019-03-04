package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UriTemplateProperty extends Property<String> {

    public UriTemplateProperty(String value) {
        super(value);
    }
}
