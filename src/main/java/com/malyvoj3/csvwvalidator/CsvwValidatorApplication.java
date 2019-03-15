package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.parser.metadata.conf.ParserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ParserConfig.class})
@SpringBootApplication
public class CsvwValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvwValidatorApplication.class, args);
    }

//    @PostConstruct
//    public void init() throws URISyntaxException {
//        String s = "file:/c:/Users/Vojta/IdeaProjects/csvw-validator/test015-user-metadata.json";
//        File a = new File(new URI(s));
//        System.out.println(a);
//    }

}
