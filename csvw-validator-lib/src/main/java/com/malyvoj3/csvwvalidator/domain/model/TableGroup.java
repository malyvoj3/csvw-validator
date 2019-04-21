package com.malyvoj3.csvwvalidator.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableGroup {

    private String id;
    private List<Note> notes = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();

}
