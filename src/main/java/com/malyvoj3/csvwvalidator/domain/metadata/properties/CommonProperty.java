package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonProperty extends Property<JsonNode> {

    /**
     * Normalized absolute iri of common properties
     */
    private String iri;

    ObjectMapper objectMapper = new ObjectMapper();

    public CommonProperty(JsonNode value, String iri) {
        super(value);
        this.iri = iri;
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        // Normalize IRI to be absolute IRI.
        iri = UriUtils.resolveCommonProperty(iri);
        // Normalize value.
        value = normalizeJsonNode(context, value);
        return normalizationErrors;
    }

    private JsonNode normalizeJsonNode(Context context, JsonNode jsonNode) {
        JsonNode normalized;
        // Normalize value.
        if (jsonNode.isArray()) {
            // Normalize each element int array.
            ArrayNode normalizeArray = objectMapper.createArrayNode();
            jsonNode.elements().forEachRemaining(element -> normalizeArray.add(normalizeJsonNode(context, element)));
            normalized = normalizeArray;
        } else {
            normalized = normalizeCommonProperty(context, jsonNode);
        }
        return normalized;
    }

    private JsonNode normalizeCommonProperty(Context context, JsonNode nonNormalizedValue) {
        JsonNode newValue = nonNormalizedValue;
        if (nonNormalizedValue.isTextual()) {
            ObjectNode normalizedObject = objectMapper.createObjectNode();
            normalizedObject.put(CsvwKeywords.VALUE_PROPERTY, nonNormalizedValue.textValue());
            if (context.getLanguage() != null) {
                normalizedObject.put(CsvwKeywords.LANGUAGE_PROPERTY, context.getLanguage().getValue());
            }
            newValue = normalizedObject;
        } else if (nonNormalizedValue.isObject() && !nonNormalizedValue.hasNonNull(CsvwKeywords.VALUE_PROPERTY)) {
            newValue = createNormalizedObject(context, nonNormalizedValue);
        }
        return newValue;
    }

    private ObjectNode createNormalizedObject(Context context, JsonNode nonNormalizedValue) {
        ObjectNode normalizedObject = objectMapper.createObjectNode();
        nonNormalizedValue.fields().forEachRemaining(entry -> {
            JsonNode entryNormalizedValue = entry.getValue();
            if (CsvwKeywords.ID_PROPERTY.equals(entry.getKey())) {
                if (entry.getValue().isTextual()) {
                    String idValue = entry.getValue().textValue();
                    // Transform compact URI to absolute.
                    idValue = UriUtils.resolveCommonProperty(idValue);
                    idValue = idValue != null ? idValue : entry.getValue().textValue();
                    // Resolve URI against base URL.
                    idValue = UriUtils.resolveUri(context.getBase().getValue(), idValue);
                    entryNormalizedValue = new TextNode(idValue);
                } else {
                    // TODO Id must not be blank node (just string is possible)
                }
            } else if (!CsvwKeywords.TYPE_PROPERTY.equals(entry.getKey())) {
                // Else behave to property as it is a common property and normalize it.
                entryNormalizedValue = normalizeJsonNode(context, entry.getValue());
            }
            normalizedObject.set(entry.getKey(), entryNormalizedValue);
        });
        return normalizedObject;
    }
}
