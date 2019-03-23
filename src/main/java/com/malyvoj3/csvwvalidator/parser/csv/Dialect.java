package com.malyvoj3.csvwvalidator.parser.csv;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Dialect {

    private char commentPrefix;
    private String delimiter;
    private String encoding;
    private char escapeCharacter;
    private int headerRowCount = 1;
    // This should not be par of dialect.
    private boolean header = true;
    private String lineTerminator;
    private char quoteCharacter;
    private boolean skipBlankRows;
    private int skipColumns;
    private int skipRows;
    private Trim trim = Trim.ALL;

}
