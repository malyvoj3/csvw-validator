package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;

public interface ObjectDescriptionParser<T extends ObjectDescription> {

    T parse(ObjectNode objectNode);

}
