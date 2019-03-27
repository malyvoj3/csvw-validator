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
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MetadataParser {

    private final TableDescriptionParser tableParser;
    private final TableGroupDescriptionParser tableGroupParser;

    public MetadataParsingResult parseJson(InputStream inputStream, String metadataUrl) {
        List<ValidationError> validationErrors = new ArrayList<>();
        MetadataParsingResult result = new MetadataParsingResult();
        result.setMetadataUrl(metadataUrl);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainNode = objectMapper.readTree(inputStream);
            if (mainNode.isObject()) {
                ObjectNode objectNode = (ObjectNode) mainNode;
                boolean hasTables = objectNode.hasNonNull(CsvwKeywords.TABLES_PROPERTY);
                boolean hasUrl = objectNode.hasNonNull(CsvwKeywords.URL_PROPERTY);
                JsonObject jsonObject = new JsonObject(null, objectNode);
                TopLevelDescription topLevelDescription = null;

                if (hasTables && !hasUrl) {
                    topLevelDescription = tableGroupParser.parse(jsonObject);
                    result.setTopLevelType(TopLevelType.TABLE_GROUP);
                } else if (hasUrl && !hasTables) {
                    topLevelDescription = tableParser.parse(jsonObject);
                    result.setTopLevelType(TopLevelType.TABLE);
                }

                if (hasTables ^ hasUrl) {
                    // If base property is null, base URL is url of metadata document.
                    Context context = topLevelDescription.getContext();
                    if (context.getBase() == null) {
                        context.setBase(new StringAtomicProperty(metadataUrl));
                    } else {
                        context.setBase(new StringAtomicProperty(UriUtils.resolveUri(metadataUrl, context.getBase().getValue())));
                    }

                    // Normalize.
                    List<ValidationError> normalizationErrors = topLevelDescription.normalize(topLevelDescription.getContext());
                    result.setTopLevelDescription(topLevelDescription);
                    result.setParsingErrors(jsonObject.getParsingErrors());
                    result.setNormalizationErrors(normalizationErrors);
                } else {
                    validationErrors.add(ValidationError.fatal("Metadata JSON does not contain valid 'url' or 'tables' property, or it contains both."));
                }

                // TODO validation - urls of table group > 1, unique names in tableSchema.columns, virtual columns after  normal,... more
            } else {
                throw new ParserException();
            }
        } catch (IOException e) {
            validationErrors.add(ValidationError.fatal(String.format("Error during parsing metadata JSON file '%s'", metadataUrl)));
        }
        result.setValidationErrors(validationErrors);
        return result;
    }

}
