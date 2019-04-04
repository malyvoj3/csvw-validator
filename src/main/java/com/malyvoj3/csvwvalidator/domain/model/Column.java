package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class Column {

    private String aboutUrl;
    private List<Cell> cells = new ArrayList<>();
    private DataType datatype;
    private String defaultValue;
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
    private Map<String, List<String>> titles = new HashMap<>();
    private String valueUrl;
    private boolean virtual;
}
