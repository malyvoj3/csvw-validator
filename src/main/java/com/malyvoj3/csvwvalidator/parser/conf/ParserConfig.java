package com.malyvoj3.csvwvalidator.parser.conf;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.*;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParser;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParser;
import com.malyvoj3.csvwvalidator.parser.metadata.factories.*;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.ContextParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig {

    // PARSERS
    @Bean
    public MetadataParser metadataParser() {
        return new MetadataParser(tableDescriptionParser(), tableGroupDescriptionParser());
    }

    @Bean
    public CsvParser csvParser() {
        return new CsvParser();
    }

    // FACTORIES
    @Bean
    public ColumnParserFactory<ColumnDescription> columnParserFactory() {
        return new ColumnParserFactory<>(dataTypeDescriptionParser());
    }

    @Bean
    public DialectParserFactory<DialectDescription> dialectParserFactory() {
        return new DialectParserFactory<>();
    }

    @Bean
    public ForeignKeyParserFactory<ForeignKeyDescription> foreignKeyParserFactory() {
        return new ForeignKeyParserFactory<>(referenceDescriptionParser());
    }

    @Bean
    public ReferenceParserFactory<ReferenceDescription> referenceParserFactory() {
        return new ReferenceParserFactory<>();
    }

    @Bean
    public SchemaParserFactory<SchemaDescription> schemaParserFactory() {
        return new SchemaParserFactory<>(dataTypeDescriptionParser(), columnDescriptionParser(), foreignKeyDescriptionParser());
    }

    @Bean
    public TableGroupParserFactory<TableGroupDescription> tableGroupParserFactory() {
        return new TableGroupParserFactory<>(dataTypeDescriptionParser(), schemaDescriptionParser(), dialectDescriptionParser(), tableDescriptionParser());
    }

    @Bean
    public TableParserFactory<TableDescription> tableParserFactory() {
        return new TableParserFactory<>(dataTypeDescriptionParser(), schemaDescriptionParser(), dialectDescriptionParser());
    }

    @Bean
    public FormatParserFactory<FormatDescription> formatParserFactory() {
        return new FormatParserFactory<>();
    }

    @Bean
    public DataTypeParserFactory<DataTypeDescription> dataTypeParserFactory() {
        return new DataTypeParserFactory<>(formatDescriptionParser());
    }

    // DESCRIPTION PARSERS
    @Bean
    public ContextParser contextParser() {
        return new ContextParser();
    }

    @Bean
    public ColumnDescriptionParser columnDescriptionParser() {
        return new ColumnDescriptionParser(columnParserFactory());
    }

    @Bean
    public DialectDescriptionParser dialectDescriptionParser() {
        return new DialectDescriptionParser(dialectParserFactory());
    }

    @Bean
    public ForeignKeyDescriptionParser foreignKeyDescriptionParser() {
        return new ForeignKeyDescriptionParser(foreignKeyParserFactory());
    }

    @Bean
    public ReferenceDescriptionParser referenceDescriptionParser() {
        return new ReferenceDescriptionParser(referenceParserFactory());
    }

    @Bean
    public SchemaDescriptionParser schemaDescriptionParser() {
        return new SchemaDescriptionParser(schemaParserFactory());
    }

    @Bean
    public TableDescriptionParser tableDescriptionParser() {
        return new TableDescriptionParser(tableParserFactory());
    }

    @Bean
    public TableGroupDescriptionParser tableGroupDescriptionParser() {
        return new TableGroupDescriptionParser(tableGroupParserFactory());
    }

    @Bean
    public FormatDescriptionParser formatDescriptionParser() {
        return new FormatDescriptionParser(formatParserFactory());
    }

    @Bean
    public DataTypeDescriptionParser dataTypeDescriptionParser() {
        return new DataTypeDescriptionParser(dataTypeParserFactory());
    }
}
