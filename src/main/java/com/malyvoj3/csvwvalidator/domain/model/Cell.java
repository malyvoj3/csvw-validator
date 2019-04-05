package com.malyvoj3.csvwvalidator.domain.model;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Cell {

    private String aboutUrl;
    private Column column;
    private List<CellError> errors = new ArrayList<>();
    private boolean ordered;
    private String propertyUrl;
    private Row row;
    private String stringValue;
    private Table table;
    private ValueType value;
    private String valueUrl;

}
