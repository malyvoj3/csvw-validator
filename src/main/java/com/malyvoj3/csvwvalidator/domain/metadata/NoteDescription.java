package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Getter;

import java.util.List;

@Getter
public class NoteDescription extends ObjectDescription {

    private CommonProperty note;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(note, context));
        return normalizationErrors;
    }
}
