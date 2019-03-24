package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.parser.csv.CsvConfig;
import com.malyvoj3.csvwvalidator.parser.metadata.conf.ParserConfig;
import com.malyvoj3.csvwvalidator.validation.conf.ValidationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@Import({ParserConfig.class, CsvConfig.class, ValidationConfig.class})
@SpringBootApplication
public class CsvwValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvwValidatorApplication.class, args);
    }

    @PostConstruct
    public void init() throws URISyntaxException, IOException {
//        String s = "file:/c/Users/Vojta/IdeaProjects/csvw-validator/test015-user-metadata.json";
//        File a = new File(new URI(s));
//        Dialect dialect = Dialect.builder().header(true).build();
//        new CsvParser().parse(dialect, "file:/c:/Users/Vojta/IdeaProjects/csvw-validator/2007-quote.csv");
    }

}
