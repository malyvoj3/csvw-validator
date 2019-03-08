package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.domain.metadata.TopLevelDescription;
import com.malyvoj3.csvwvalidator.parser.metadata.Parser;
import com.malyvoj3.csvwvalidator.parser.metadata.conf.ParserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Import({ParserConfig.class})
@SpringBootApplication
public class CsvwValidatorApplication {

    @Autowired
    private Parser parser;

	public static void main(String[] args) throws IOException {
        SpringApplication.run(CsvwValidatorApplication.class, args);

        /*Parser parser = new Parser();
        TopLevelDescription description = parser.parseJson(inputStream);
        System.out.println(description);*/
    }

    @PostConstruct
    private void init() throws FileNotFoundException {
	    String metadataURL = "test015-user-metadata.json";
        InputStream inputStream = new FileInputStream(metadataURL);
        TopLevelDescription description = parser.parseJson(inputStream, metadataURL);
        System.out.println(description);
    }
}
