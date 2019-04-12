package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;

import lombok.NonNull;

public class IdPropertyParser<T extends ObjectDescription> implements PropertyParser<T> {

  private static final String BLANK_NODE_PREFIX = "_";

  @Override
  public void parsePropertyToDescription(@NonNull T description, @NonNull JsonProperty jsonProperty) {
    JsonNode property = jsonProperty.getJsonValue();
    if (property.isTextual()) {
      if (!property.textValue().startsWith(BLANK_NODE_PREFIX)) {
        description.setId(new LinkProperty(property.textValue()));
      } else {
        jsonProperty.addError(JsonParserError.isBlankNode(jsonProperty.getName()));
      }
    } else {
      jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
    }
  }

}
