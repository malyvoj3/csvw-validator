package com.malyvoj3.csvwvalidator.parser.metadata;

import java.io.InputStream;

public interface MetadataParser {

    MetadataParsingResult parseJson(InputStream inputStream, String metadataUrl);
}
