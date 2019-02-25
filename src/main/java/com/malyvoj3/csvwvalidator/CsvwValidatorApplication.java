package com.malyvoj3.csvwvalidator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//@SpringBootApplication
public class CsvwValidatorApplication {

	public static void main(String[] args) throws IOException {
        //SpringApplication.run(CsvwValidatorApplication.class, args);
        InputStream inputStream = new FileInputStream("test013-user-metadata.json");
        //Map<String, Object> map = (Map) JsonUtils.fromInputStream(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mainNode = objectMapper.readTree(inputStream);
        ObjectNode objectNode = (ObjectNode) mainNode;
        System.out.println(mainNode);
    }
}
