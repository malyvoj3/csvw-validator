package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Parser {

    // TODO string bean?
    private TableDescriptionParser tableParser = new TableDescriptionParser();
    private TableGroupDescriptionParser tableGroupParser = new TableGroupDescriptionParser();

    public TopLevelDescription parseJson(InputStream inputStream) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainNode = objectMapper.readTree(inputStream);
            if (mainNode.isObject()) {
                ObjectNode mainObject = (ObjectNode) mainNode;
                JsonNode tables = mainObject.findValue(CsvwKeywords.TABLES_PROPERTY);
                JsonNode url = mainObject.findValue(CsvwKeywords.URL_PROPERTY);
                if (tables != null) {
                    return tableGroupParser.parse(mainObject);
                } else if (url != null) {
                    return tableParser.parse(mainObject);
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
