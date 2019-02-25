package com.malyvoj3.csvwvalidator.parser.metatada;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.factory.TableGroupPropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;
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
                JsonNode context = mainObject.findValue(CsvwKeywords.CONTEXT_KEY);
                JsonNode url = mainObject.findValue(CsvwKeywords.URL_KEY);
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
