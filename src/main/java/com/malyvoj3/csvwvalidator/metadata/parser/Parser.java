package com.malyvoj3.csvwvalidator.metadata.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.TableDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.TableGroupDescription;
import com.malyvoj3.csvwvalidator.metadata.domain.TopLevelDescription;
import com.malyvoj3.csvwvalidator.metadata.parser.factory.TableGroupPropertyParserFactory;
import com.malyvoj3.csvwvalidator.metadata.parser.properties.PropertyParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Parser {

    public TopLevelDescription parseJson(InputStream inputStream) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainNode = objectMapper.readTree(inputStream);
            if (mainNode.isObject()) {
                ObjectNode mainObject = (ObjectNode) mainNode;
                JsonNode context = mainObject.findValue(CsvwKeywords.CONTEXT_PROPERTY);
                JsonNode url = mainObject.findValue(CsvwKeywords.URL_PROPERTY);
                if (context != null) {
                    return parseTableGroup(mainObject);
                } else if (url != null) {
                    return parseTable(mainObject);
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

    private TableDescription parseTable(ObjectNode tableObject) {
        return null;
    }

    private TableGroupDescription parseTableGroup(ObjectNode tableGroupObject) {
        TableGroupDescription tableGroup = new TableGroupDescription();
        tableGroupObject.fields().forEachRemaining(entry -> {
            PropertyParser<TableGroupDescription> propertyParser =
                    TableGroupPropertyParserFactory.createParser(entry.getKey());
            if (propertyParser != null) {
                propertyParser.parseProperty(tableGroup, entry.getValue());
            } else {
                // TODO properly logged WARNING
                log.warn("Unknown property");
            }
        });
        return tableGroup;
    }

}
