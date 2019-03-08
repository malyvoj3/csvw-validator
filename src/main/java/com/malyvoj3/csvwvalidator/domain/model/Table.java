package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Table {

    private List<Column> columns = new ArrayList<>();
    private TableDirection tableDirection;
    // TODO foreignKeys
    private String id;
    // TODO notes
    private String schema;
    private Boolean suppressOutput;
    // TODO transformations
    private String url;

}