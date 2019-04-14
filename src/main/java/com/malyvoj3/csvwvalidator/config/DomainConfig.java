package com.malyvoj3.csvwvalidator.config;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public DataTypeFactory datatypeFactory() {
        return new DataTypeFactory();
    }

}
