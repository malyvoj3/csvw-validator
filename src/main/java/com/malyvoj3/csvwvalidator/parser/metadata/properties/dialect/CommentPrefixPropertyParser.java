package com.malyvoj3.csvwvalidator.parser.metadata.properties.dialect;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.DialectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.properties.PropertyParser;

public class CommentPrefixPropertyParser<T extends DialectDescription> implements PropertyParser<T> {

    private static final String COMMENT_PREFIX_DEFAULT_VALUE = "#";

    @Override
    public T parseProperty(T description, JsonNode property) {
        StringAtomicProperty commentPrefix;
        if (property.isTextual()) {
            commentPrefix = new StringAtomicProperty(property.textValue());
        } else {
            commentPrefix = new StringAtomicProperty(COMMENT_PREFIX_DEFAULT_VALUE);
        }
        description.setCommentPrefix(commentPrefix);
        return description;
    }

}
