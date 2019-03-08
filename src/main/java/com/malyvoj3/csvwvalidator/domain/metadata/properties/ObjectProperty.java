package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonObject;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.ContextParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.ObjectDescriptionParser;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ObjectProperty<T extends ObjectDescription> extends Property<T> {

    private String objectUrl;
    private ObjectDescriptionParser<T> objectParser;

    ObjectMapper objectMapper = new ObjectMapper();
    private ContextParser contextParser = new ContextParser();

    public ObjectProperty(T objectValue) {
        super(objectValue);
    }

    public ObjectProperty(String objectUrl, ObjectDescriptionParser<T> objectParser) {
        super(null);
        this.objectUrl = objectUrl;
        this.objectParser = objectParser;
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        if (objectUrl != null) {
            normalizeUrlObject(context);
        } else {
            normalizeEmbeddedObject(context);
        }
        return normalizationErrors;
    }

    private void normalizeUrlObject(Context context) {
        String normalizedUrl = UriUtils.resolveAndNormalizeUri(context.getBase().getValue(), objectUrl);
        JsonNode node = null;
        try {
            node = objectMapper.readTree(normalizedUrl);
            if (node != null && node.isObject()) {
                JsonObject jsonObject = new JsonObject(null, (ObjectNode) node);
                JsonProperty jsonProperty = new JsonProperty(null, node);
                T parsedObject = objectParser.parse(jsonObject);
                Context newContext = contextParser.parse(jsonProperty);
                // TODO get errors from parsing.
                parsedObject.normalize(newContext != null ? newContext : context);
                if (parsedObject.getId() == null) {
                    parsedObject.setId(new LinkProperty(objectUrl));
                }
                value = parsedObject;
            } else {
                // TODO: log
            }
        } catch (IOException e) {
            // TODO: log
        }
    }

    private void normalizeEmbeddedObject(Context context) {
        value.normalize(context);
    }
}
