package com.malyvoj3.csvwvalidator.parser.tabular;

public interface TabularDataParser {

    TabularParsingResult parse(Dialect dialect, String url, String filePath);

}
