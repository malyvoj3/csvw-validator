package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Table {

    private List<Column> columns = new ArrayList<>();
    private String id;
    private String schema;
    private boolean suppressOutput;
    private String url;

}
