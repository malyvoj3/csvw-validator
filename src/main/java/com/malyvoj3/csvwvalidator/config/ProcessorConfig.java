package com.malyvoj3.csvwvalidator.config;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import com.malyvoj3.csvwvalidator.parser.metadata.DefaultMetadataParser;
import com.malyvoj3.csvwvalidator.parser.tabular.CsvParser;
import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.CsvwShemaLocator;
import com.malyvoj3.csvwvalidator.processor.DefaultAnnotationCreator;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResultCreator;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.TextResultWriter;
import com.malyvoj3.csvwvalidator.validation.metadata.DefaultMetadataValidator;
import com.malyvoj3.csvwvalidator.validation.model.DefaultModelValidator;
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
    private final DefaultMetadataParser metadataParser;
    private final DefaultMetadataValidator metadataValidator;
    private final DefaultModelValidator modelValidator;
    private final DataTypeFactory dataTypeFactory;

    @Bean
    public CsvwProcessor csvwProcessor() {
        return new CsvwProcessor(csvParser, metadataParser, siteWideLocator(),
                annotationCreator(), metadataValidator, modelValidator, resultCreator());
    }

    @Bean
    public CsvwShemaLocator siteWideLocator() {
        return new CsvwShemaLocator();
    }

    @Bean
    public DefaultAnnotationCreator annotationCreator() {
        return new DefaultAnnotationCreator(dataTypeFactory);
    }

    @Bean
    public ProcessingResultCreator resultCreator() {
        return new ProcessingResultCreator();
    }

    @Bean
    public CsvResultWriter csvResultWriter() {
        return new CsvResultWriter();
    }

    @Bean
    public RdfResultWriter rdfResultWriter() {
        return new RdfResultWriter();
    }

    @Bean
    public TextResultWriter textResultWriter() {
        return new TextResultWriter();
    }

}
