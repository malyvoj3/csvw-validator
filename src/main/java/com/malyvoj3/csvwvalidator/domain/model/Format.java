package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Format {

    private String decimalChar;
    private String groupChar;
    private String pattern;

}
