package com.malyvoj3.csvwvalidator.parser.metadata.factories;

import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ParserFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.IdPropertyParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.TypePropertyParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

public class ObjectDescriptionParserFactory<T extends ObjectDescription> implements ParserFactory<T> {

  public PropertyParser<T> createParser(String propertyName) {
    switch (propertyName) {
      case CsvwKeywords.ID_PROPERTY:
        return new IdPropertyParser<>();
      case CsvwKeywords.TYPE_PROPERTY:
        return new TypePropertyParser<>();
      default:
        return null;
    }
  }

}
