package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LinkProperty extends Property<String> {

    public LinkProperty(String value) {
        super(value);
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        value = UriUtils.resolveUri(context.getBase().getValue(), value);
        return normalizationErrors;
    }
}
