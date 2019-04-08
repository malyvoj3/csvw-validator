package com.malyvoj3.csvwvalidator.processor.conf;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import com.malyvoj3.csvwvalidator.parser.conf.ParserConfig;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.processor.*;
import com.malyvoj3.csvwvalidator.validation.conf.ValidationConfig;
import com.malyvoj3.csvwvalidator.validation.metadata.MetadataValidator;
import com.malyvoj3.csvwvalidator.validation.model.ModelValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        ParserConfig.class,
        ValidationConfig.class
})
@RequiredArgsConstructor
@Configuration
public class ProcessorConfig {

    private final CsvParser csvParser;
    private final MetadataParser metadataParser;
    private final MetadataValidator metadataValidator;
    private final ModelValidator modelValidator;
    private final DataTypeFactory dataTypeFactory;

    @Bean
    public CsvwProcessor csvwProcessor() {
        return new CsvwProcessor(csvParser, metadataParser, siteWideLocator(),
                annotationCreator(), metadataValidator, modelValidator, resultCreator());
    }

    @Bean
    public SiteWideLocator siteWideLocator() {
        return new SiteWideLocator();
    }

    @Bean
    public AnnotationCreator annotationCreator() {
        return new AnnotationCreator(dataTypeFactory);
    }

    @Bean
    public ResultCreator resultCreator() {
        return new ResultCreator();
    }

    @Bean
    public CsvResultWriter csvResultWriter() {
        return new CsvResultWriter();
    }

}
