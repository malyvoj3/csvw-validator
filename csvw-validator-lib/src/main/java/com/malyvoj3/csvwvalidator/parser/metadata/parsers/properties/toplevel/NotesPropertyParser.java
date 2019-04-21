package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.toplevel;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.NoteDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class NotesPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        List<NoteDescription> notes = new ArrayList<>();
        if (property.isArray()) {
            property.elements().forEachRemaining(element -> notes.add(new NoteDescription(new CommonProperty(element, null))));
        }
        if (!notes.isEmpty()) {
            description.setNotes(new ArrayProperty<>(notes));
        }
    }
}
