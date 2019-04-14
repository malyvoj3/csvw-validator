package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class CommentPrefixPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String COMMENT_PREFIX_DEFAULT_VALUE = "#";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty commentPrefix;
        if (property.isTextual()) {
            commentPrefix = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
            commentPrefix = new StringAtomicProperty(COMMENT_PREFIX_DEFAULT_VALUE);
        }
        description.setCommentPrefix(commentPrefix);
    }

}
