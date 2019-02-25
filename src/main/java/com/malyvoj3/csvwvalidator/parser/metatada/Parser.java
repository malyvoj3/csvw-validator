package com.malyvoj3.csvwvalidator.parser.metatada;

import com.github.jsonldjava.utils.JsonUtils;
import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.domain.metadata.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metatada.factory.TableGroupPropertyParserFactory;
import com.malyvoj3.csvwvalidator.parser.metatada.properties.PropertyParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Parser {

    public TopLevelDescription parseJson(InputStream inputStream) {
        try {
            Object parsedFile = JsonUtils.fromInputStream(inputStream);
            if (parsedFile instanceof Map) {
                Map<String, Object> jsonMap = (Map) parsedFile;
                Object context = jsonMap.get(CsvwKeywords.CONTEXT_KEY);
                Object url = jsonMap.get(CsvwKeywords.URL_KEY);
                if (context != null) {
                    return parseTableGroup(jsonMap);
                } else if (url != null) {
                    return parseTable(jsonMap);
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

    private TableDescription parseTable(Map<String, Object> map) {
        return null;
    }

    private TableGroupDescription parseTableGroup(Map<String, Object> map) {
        TableGroupDescription tableGroup = new TableGroupDescription();
        for (String key : map.keySet()) {
            PropertyParser<TableGroupDescription> propertyParser = TableGroupPropertyParserFactory.createParser(key);
            if (propertyParser != null) {
                propertyParser.parseProperty(tableGroup);
            } else {

            }
        }
        return tableGroup;
    }

}
