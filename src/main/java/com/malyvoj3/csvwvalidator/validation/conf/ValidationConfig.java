package com.malyvoj3.csvwvalidator.validation.conf;

import com.malyvoj3.csvwvalidator.validation.AnnotationCreator;
import com.malyvoj3.csvwvalidator.validation.CsvwProcessor;
import com.malyvoj3.csvwvalidator.validation.SiteWideLocator;
import com.malyvoj3.csvwvalidator.validation.metadata.MetadataValidator;
import com.malyvoj3.csvwvalidator.validation.metadata.TablesRequiredRule;
import com.malyvoj3.csvwvalidator.validation.metadata.UrlRequiredRule;
import com.malyvoj3.csvwvalidator.validation.model.ModelValidator;
import com.malyvoj3.csvwvalidator.validation.model.PrimaryKeyRule;
import com.malyvoj3.csvwvalidator.validation.model.RequiredColumnRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public CsvwProcessor csvwProcessor() {
        return new CsvwProcessor();
    }

    @Bean
    public SiteWideLocator siteWideLocator() {
        return new SiteWideLocator();
    }

    @Bean
    public AnnotationCreator annotationCreator() {
        return new AnnotationCreator();
    }

    @Bean
    public MetadataValidator metadataValidator() {
        return new MetadataValidator();
    }

    @Bean
    public ModelValidator modelValidator() {
        return new ModelValidator();
    }

    @Bean
    public TablesRequiredRule tablesRequiredRule() {
        return new TablesRequiredRule();
    }

    @Bean
    public UrlRequiredRule urlRequiredRule() {
        return new UrlRequiredRule();
    }

    @Bean
    public RequiredColumnRule requiredColumnRule() {
        return new RequiredColumnRule();
    }

    @Bean
    public PrimaryKeyRule primaryKeyRule() {
        return new PrimaryKeyRule();
    }
}
