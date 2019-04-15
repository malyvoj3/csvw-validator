package com.malyvoj3.csvwvalidator.parser.tabular;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public interface TabularDataParser {

    TabularParsingResult parse(Dialect dialect, String url) throws URISyntaxException, IOException;

    TabularParsingResult parse(Dialect dialect, String url, InputStream inputStream) throws IOException;

    TabularParsingResult parse(Dialect dialect, String url, byte[] file);

}
