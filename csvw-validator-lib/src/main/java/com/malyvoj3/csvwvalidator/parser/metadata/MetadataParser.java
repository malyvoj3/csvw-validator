package com.malyvoj3.csvwvalidator.parser.metadata;

import java.io.InputStream;

public interface MetadataParser {

    MetadataParsingResult parseJson(String filePath, ParsingContext parsingContext);
    MetadataParsingResult parseJson(InputStream inputStream, ParsingContext parsingContext);
}
