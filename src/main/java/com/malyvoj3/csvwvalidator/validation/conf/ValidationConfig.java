package com.malyvoj3.csvwvalidator.validation.conf;

import com.malyvoj3.csvwvalidator.validation.SiteWideLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public SiteWideLocator siteWideLocator() {
        return new SiteWideLocator();
    }

}
