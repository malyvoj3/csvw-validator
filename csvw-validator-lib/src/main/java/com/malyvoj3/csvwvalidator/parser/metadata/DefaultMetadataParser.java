package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableGroupDescriptionParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DefaultMetadataParser implements MetadataParser {

    private final TableDescriptionParser tableParser;
    private final TableGroupDescriptionParser tableGroupParser;

    @Override
    public MetadataParsingResult parseJson(String filePath, ParsingContext parsingContext) {
        MetadataParsingResult result = new MetadataParsingResult();
        result.setMetadataUrl(parsingContext.getMetadataUrl());
        try {
            File file = new File(new URI(filePath));
            result = parseJson(new FileInputStream(file), parsingContext);
        } catch (Exception ex) {
            List<ValidationError> validationErrors = new ArrayList<>();
            validationErrors.add(ValidationError.fatal("error.invalidJson", parsingContext.getMetadataUrl()));
            result.setValidationErrors(validationErrors);
        }
        return result;

    }

    @Override
    public MetadataParsingResult parseJson(InputStream inputStream, ParsingContext parsingContext) {
        List<ValidationError> validationErrors = new ArrayList<>();
        MetadataParsingResult result = new MetadataParsingResult();
        result.setMetadataUrl(parsingContext.getMetadataUrl());
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
                        context.setBase(new StringAtomicProperty(parsingContext.getMetadataUrl()));
                    } else {
                        context.setBase(new StringAtomicProperty(UriUtils.resolveUri(parsingContext.getMetadataUrl(), context.getBase().getValue())));
                    }

                    // Normalize.
                    List<ValidationError> normalizationErrors = topLevelDescription.normalize(topLevelDescription.getContext());
                    result.setTopLevelDescription(topLevelDescription);
                    result.setParsingErrors(jsonObject.getErrors());
                    result.setNormalizationErrors(normalizationErrors);
                } else {
                    validationErrors.add(ValidationError.fatal("error.notTopLevel"));
                }
            } else {
                throw new ParserException();
            }
        } catch (IOException e) {
            validationErrors.add(ValidationError.fatal("error.invalidJson", parsingContext.getMetadataUrl()));
        }
        result.setValidationErrors(validationErrors);
        return result;
    }

}
