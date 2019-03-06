package com.malyvoj3.csvwvalidator.parser.metadata.conf;

import com.malyvoj3.csvwvalidator.domain.metadata.*;
import com.malyvoj3.csvwvalidator.parser.metadata.Parser;
import com.malyvoj3.csvwvalidator.parser.metadata.factory.*;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.ContextParser;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.descriptions.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig {

    // FACTORIES
    @Bean
    public ColumnParserFactory<ColumnDescription> columnParserFactory() {
        return new ColumnParserFactory<>();
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
        return new SchemaParserFactory<>(columnDescriptionParser(), foreignKeyDescriptionParser());
    }

    @Bean
    public TableGroupParserFactory<TableGroupDescription> tableGroupParserFactory() {
        return new TableGroupParserFactory<>(schemaDescriptionParser(), tableDescriptionParser());
    }

    @Bean
    public TableParserFactory<TableDescription> tableParserFactory() {
        return new TableParserFactory<>(schemaDescriptionParser());
    }

    // DESCRIPTION PARSERS
    @Bean
    public Parser parser() {
        return new Parser(tableDescriptionParser(), tableGroupDescriptionParser());
    }

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
}
