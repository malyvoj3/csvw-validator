package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//@SpringBootApplication
public class CsvwValidatorApplication {

	public static void main(String[] args) throws IOException {
        //SpringApplication.run(CsvwValidatorApplication.class, args);
        InputStream inputStream = new FileInputStream("test015-user-metadata.json");
//        Map<String, Object> map = (Map) JsonUtils.fromInputStream(inputStream);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode mainNode = objectMapper.readTree(inputStream);
//        ObjectNode objectNode = (ObjectNode) mainNode;
//        System.out.println(mainNode);
        Parser parser = new Parser();
        TopLevelDescription description = parser.parseJson(inputStream);
        System.out.println(description);
    }
}
