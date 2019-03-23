package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
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
    private boolean ordered;
    private String propertyUrl;
    private boolean required;
    private String separator;
    private Integer sourceNumber;
    private boolean suppressOutput;
    private Table table;
    private TextDirection textDirection;
    private List<String> titles = new ArrayList<>();
    private String valueUrl;
    private boolean virtual;
}
