package com.malyvoj3.csvwvalidator.model.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Row {

    private List<Cell> cells = new ArrayList<>();
    private Integer number;
    private List<Cell> primaryKey = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    // TODO referenced rows
    private Integer sourceNumber;
    private Table table;
}
