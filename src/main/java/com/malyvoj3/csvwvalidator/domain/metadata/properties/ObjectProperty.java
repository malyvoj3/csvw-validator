package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.ContextParser;
import com.malyvoj3.csvwvalidator.parser.metadata.ObjectDescriptionParser;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    public void normalize(Context context) {
        if (objectUrl != null) {
            normalizeUrlObject(context);
        } else {
            normalizeEmbeddedObject(context);
        }
    }

    private void normalizeUrlObject(Context context) {
        String normalizedUrl = UriUtils.resolveAndNormalizeUri(context.getBase().getValue(), objectUrl);
        JsonNode node = null;
        try {
            node = objectMapper.readTree(normalizedUrl);
            if (node != null && node.isObject()) {
                T parsedObject = objectParser.parse((ObjectNode) node);
                Context newContext = contextParser.parse(node);
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
