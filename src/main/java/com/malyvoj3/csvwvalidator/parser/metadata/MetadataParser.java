package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableGroupDescriptionParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MetadataParser {

    private final TableDescriptionParser tableParser;
    private final TableGroupDescriptionParser tableGroupParser;

    public MetadataParsingResult parseJson(InputStream inputStream, String metadataURL) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainNode = objectMapper.readTree(inputStream);
            if (mainNode.isObject()) {
                MetadataParsingResult result = new MetadataParsingResult();
                ObjectNode objectNode = (ObjectNode) mainNode;
                JsonNode tables = objectNode.findValue(CsvwKeywords.TABLES_PROPERTY);
                JsonNode url = objectNode.findValue(CsvwKeywords.URL_PROPERTY);
                JsonObject jsonObject = new JsonObject(null, objectNode);
                TopLevelDescription topLevelDescription;

                if (tables != null && url == null) {
                    topLevelDescription = tableGroupParser.parse(jsonObject);
                } else if (url != null && tables == null) {
                    topLevelDescription = tableParser.parse(jsonObject);
                } else {
                    throw new ParserException();
                }

                // If base property is null, base URL is url of metadata document.
                Context context = topLevelDescription.getContext();
                if (context.getBase() == null) {
                    context.setBase(new StringAtomicProperty(metadataURL));
                }

                // Normalize.
                List<ValidationError> normalizationErrors = topLevelDescription.normalize(topLevelDescription.getContext());
                result.setTopLevelDescription(topLevelDescription);
                result.setParsingErrors(jsonObject.getParsingErrors());
                result.setNormalizationErrors(normalizationErrors);
                return result;
            } else {
                throw new ParserException();
            }
        } catch (IOException e) {
            throw new ParserException();
        }
    }

}
