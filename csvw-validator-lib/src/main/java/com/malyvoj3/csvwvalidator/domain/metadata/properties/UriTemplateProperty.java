package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UriTemplateProperty extends Property<String> {

    public UriTemplateProperty(String value) {
        super(value);
    }
}
