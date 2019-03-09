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

}
