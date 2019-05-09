package com.malyvoj3.csvwvalidator.parser.metadata;

import java.io.InputStream;

/**
 * Parse JSON-LD descriptor and retrieve metadata.
 */
public interface MetadataParser {

    /**
     * Parse JSON-LD descriptor given by path.
     * @param filePath Descriptor file path.
     * @param parsingContext Parsing context.
     * @return Parsed metadata.
     */
    MetadataParsingResult parseJson(String filePath, ParsingContext parsingContext);

    /**
     * Parse JSON-LD descriptor given by file.
     * @param inputStream Descriptor stream
     * @param parsingContext Parsing context.
     * @return Parsed metadata.
     */
    MetadataParsingResult parseJson(InputStream inputStream, ParsingContext parsingContext);
}
