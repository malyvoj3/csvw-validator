package com.malyvoj3.csvwvalidator;

import com.fasterxml.jackson.core.*;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

//@SpringBootApplication
public class CsvwValidatorApplication {

	public static void main(String[] args) throws IOException {
        //SpringApplication.run(CsvwValidatorApplication.class, args);
        InputStream inputStream = new FileInputStream("test013-user-metadata.json");
        Map<String, Object> map = (Map) JsonUtils.fromInputStream(inputStream);
        System.out.println(map);
    }
}
