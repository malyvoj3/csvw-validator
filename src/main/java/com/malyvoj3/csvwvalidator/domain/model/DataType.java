package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataType {

    private String id;
    private String base;
    private Format format;

    private Long length;
    private Long minLength;
    private Long maxLength;

    private String minimum;
    private String maximum;
    private String minExclusive;
    private String maxExclusive;

}
