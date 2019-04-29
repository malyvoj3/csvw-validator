package com.malyvoj3.csvwvalidator.config;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import com.malyvoj3.csvwvalidator.validation.metadata.*;
import com.malyvoj3.csvwvalidator.validation.model.DefaultModelValidator;
import com.malyvoj3.csvwvalidator.validation.model.PrimaryKeyRuleFactory;
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
    public DefaultMetadataValidator metadataValidator() {
        return new DefaultMetadataValidator();
    }

    @Bean
    public DefaultModelValidator modelValidator() {
        return new DefaultModelValidator(dataTypeFactory);
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
    public PrimaryKeyRuleFactory primaryKeyRuleFactory() {
        return new PrimaryKeyRuleFactory();
    }

    @Bean
    public StringFormatRegexRule stringFormatRegexRule() {
        return new StringFormatRegexRule();
    }

    @Bean
    public DataTypesValidationRule dataTypesValidationRule() {
        return new DataTypesValidationRule(dataTypeFactory);
    }

    @Bean
    public VirtualColumnOrderRule virtualColumnOrderRule() {
        return new VirtualColumnOrderRule();
    }

    @Bean
    public CommonPropertiesSyntaxRule commonPropertiesSyntaxRule() {
        return new CommonPropertiesSyntaxRule();
    }

    @Bean
    public ColumnNamesRule columnNamesRule() {
        return new ColumnNamesRule();
    }

    @Bean
    public PrimaryKeyReferenceRule primaryKeyReferenceRule() {
        return new PrimaryKeyReferenceRule();
    }
}
