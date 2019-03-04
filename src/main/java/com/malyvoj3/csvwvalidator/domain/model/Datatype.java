package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Data;

@Data
public class Datatype {

    private String id;
    private String base;

    // TODO format

    private Integer length;
    private Integer minimumLength;
    private Integer maximumLength;

    private String minimum;
    private String maximum;
    private String minimumExclusive;
    private String maximumExclusive;

}
