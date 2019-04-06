package com.malyvoj3.csvwvalidator.validation.conf;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import com.malyvoj3.csvwvalidator.domain.conf.DomainConfig;
import com.malyvoj3.csvwvalidator.validation.metadata.*;
import com.malyvoj3.csvwvalidator.validation.model.ModelValidator;
import com.malyvoj3.csvwvalidator.validation.model.PrimaryKeyRule;
import com.malyvoj3.csvwvalidator.validation.model.RequiredColumnRule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({DomainConfig.class})
@RequiredArgsConstructor
@Configuration
public class ValidationConfig {

    private final DataTypeFactory dataTypeFactory;

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

    @Bean
    public StringFormatRegexRule stringFormatRegexRule() {
        return new StringFormatRegexRule();
    }

    @Bean
    public DataTypesValidationRule dataTypesValidationRule() {
        return new DataTypesValidationRule(dataTypeFactory);
    }
}
