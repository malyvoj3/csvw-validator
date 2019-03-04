package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonProperty extends Property<JsonNode> {

    private String compactIRI;
    ObjectMapper objectMapper = new ObjectMapper();

  public CommonProperty(JsonNode value, String compactIRI) {
    super(value);
    this.compactIRI = compactIRI;
  }

  @Override
    public void normalize(Context context) {
        if (value.isArray()) {
            // Normalize each element int array.
            ArrayNode normalizeArray = objectMapper.createArrayNode();
            value.elements().forEachRemaining(jsonNode -> {
                normalizeArray.add(normalizeCommonProperty(context));
            });
        } else {
          value = normalizeCommonProperty(context);
        }
    }

    private JsonNode normalizeCommonProperty(Context context) {
        if (value.isTextual()) {
            ObjectNode normalizedObject = objectMapper.createObjectNode();
            normalizedObject.put(CsvwKeywords.VALUE_PROPERTY, value.textValue());
            if (context.getLanguage() != null) {
                normalizedObject.put(CsvwKeywords.LANGUAGE_PROPERTY, context.getLanguage().getValue());
            }
        } else if (value.isObject() && !value.hasNonNull(CsvwKeywords.VALUE_PROPERTY)) {
            value.fields().forEachRemaining(entry -> {
                if (CsvwKeywords.ID_PROPERTY.equals(entry.getKey())) {
                    // TODO prelozit kompaktni URL na base
                    //nahradit kompaktni IRI za absolutni
                    //reslovnout vuci base a normalizovat
                } else if (!CsvwKeywords.TYPE_PROPERTY.equals(entry.getKey())) {
                    // Else behave to property as it is a common property and normalize it.
                    entry.setValue(normalizeCommonProperty(context));
                }
            });
        }
        return null;
    }
}
