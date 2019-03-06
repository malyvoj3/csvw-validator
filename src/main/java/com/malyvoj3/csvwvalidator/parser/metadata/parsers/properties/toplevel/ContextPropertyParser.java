package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.toplevel;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.ContextParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class ContextPropertyParser<T extends TopLevelDescription> implements PropertyParser<T> {

    private ContextParser parser = new ContextParser();

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        Context context = parser.parse(jsonProperty);
        description.setContext(context);
    }

}
