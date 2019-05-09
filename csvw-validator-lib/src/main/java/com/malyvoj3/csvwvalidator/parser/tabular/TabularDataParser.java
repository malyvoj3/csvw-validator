package com.malyvoj3.csvwvalidator.parser.tabular;

/**
 * Tabular data parser.
 */
public interface TabularDataParser {

    /**
     * Parse and validates tabular data file.
     * @param dialect Dialect of the tabular file.
     * @param url Url of the file.
     * @param filePath Path of temporary file, which was download from {@code url}.
     * @param remoteFile Is the file local or remote.
     * @return {@link TabularParsingResult} result of parsing this file.
     */
    TabularParsingResult parse(Dialect dialect, String url, String filePath, boolean remoteFile);

}
