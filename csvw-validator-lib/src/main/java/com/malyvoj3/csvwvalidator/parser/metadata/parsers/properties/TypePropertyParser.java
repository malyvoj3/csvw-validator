package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonParserError;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import lombok.NonNull;

public class TypePropertyParser<T extends ObjectDescription> implements PropertyParser<T> {

  @Override
  public void parsePropertyToDescription(@NonNull T description, @NonNull JsonProperty jsonProperty) {
    JsonNode property = jsonProperty.getJsonValue();
    if (property.isTextual()) {
      if (property.textValue().equals(description.getValidType())) {
        description.setType(new StringAtomicProperty(property.textValue()));
      } else {
        jsonProperty.addError(JsonParserError.invalidType(jsonProperty.getName(), description.getValidType()));
      }
    } else {
      jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
    }
  }

}
