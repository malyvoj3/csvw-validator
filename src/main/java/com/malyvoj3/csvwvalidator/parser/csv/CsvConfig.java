package com.malyvoj3.csvwvalidator.parser.csv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvConfig {

    @Bean
    public CsvParser csvParser() {
        return new CsvParser();
    }
}
