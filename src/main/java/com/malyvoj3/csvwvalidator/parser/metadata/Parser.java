package com.malyvoj3.csvwvalidator.parser.metadata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableDescriptionParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.TableGroupDescriptionParser;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
public class Parser {

    private final TableDescriptionParser tableParser;
    private final TableGroupDescriptionParser tableGroupParser;

    public TopLevelDescription parseJson(InputStream inputStream) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainNode = objectMapper.readTree(inputStream);
            if (mainNode.isObject()) {
                ObjectNode objectNode = (ObjectNode) mainNode;
                JsonNode tables = objectNode.findValue(CsvwKeywords.TABLES_PROPERTY);
                JsonNode url = objectNode.findValue(CsvwKeywords.URL_PROPERTY);
                JsonObject jsonObject = new JsonObject(null, objectNode);
                if (tables != null) {
                    return tableGroupParser.parse(jsonObject);
                } else if (url != null) {
                    return tableParser.parse(jsonObject);
                } else {
                    throw new ParserException();
                }
            } else {
                throw new ParserException();
            }
        } catch (IOException e) {
            throw new ParserException();
        }
    }

}
