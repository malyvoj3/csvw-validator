package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.common;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.CommonDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class CommonPropertyParser<T extends CommonDescription> implements PropertyParser<T> {

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        CommonProperty commonProperty = new CommonProperty(jsonProperty.getJsonValue(), jsonProperty.getName());
        description.addCommonProperty(commonProperty);
    }

}
