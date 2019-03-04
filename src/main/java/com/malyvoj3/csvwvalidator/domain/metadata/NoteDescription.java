package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;

import lombok.Getter;

@Getter
public class NoteDescription extends ObjectDescription {

    private CommonProperty note;

    @Override
    public void normalize(Context context) {
        normalizeProperty(note, context);
    }
}
