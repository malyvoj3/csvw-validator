package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LinkProperty extends Property<String> {

    public LinkProperty(String value) {
        super(value);
    }

    @Override
    public void normalize(Context context) {
        value = UriUtils.resolveAndNormalizeUri(context.getBase().getValue(), value);
    }
}
