package com.malyvoj3.csvwvalidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//@SpringBootApplication
public class CsvwValidatorApplication {

	public static void main(String[] args) throws IOException {
        //SpringApplication.run(CsvwValidatorApplication.class, args);
        InputStream inputStream = new FileInputStream("test015-user-metadata.json");

        /*Parser parser = new Parser();
        TopLevelDescription description = parser.parseJson(inputStream);
        System.out.println(description);*/
    }
}
