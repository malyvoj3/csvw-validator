package com.malyvoj3.csvwvalidator.metadata.parser.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.metadata.domain.DialectDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;

public class CommentPrefixPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String COMMENT_PREFIX_DEFAULT_VALUE = "#";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty commentPrefix;
        if (property.isTextual()) {
            commentPrefix = new StringAtomicProperty(property, property.textValue());
        } else {
            commentPrefix = new StringAtomicProperty(property, COMMENT_PREFIX_DEFAULT_VALUE);
        }
        description.setCommentPrefix(commentPrefix);
        return description;
    }

}
