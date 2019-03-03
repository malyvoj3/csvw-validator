package com.malyvoj3.csvwvalidator.model.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Column {

    private String aboutUrl;
    private List<Cell> cells = new ArrayList<>();
    private Datatype datatype;
    private Object defaultValue;
    private String lang;
    private String name;
    private List<String> nullValues = new ArrayList<>();
    private Integer number;
    private Boolean ordered;
    private String propertyUrl;
    private Boolean required;
    private String separator;
    private Integer sourceNumber;
    private Boolean suppressOutput;
    private Table table;
    private TextDirection textDirection;
    private List<String> titles;
    private String valueUrl;
    private Boolean virtual;
}
