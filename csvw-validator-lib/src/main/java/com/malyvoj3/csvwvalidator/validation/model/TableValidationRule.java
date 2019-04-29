package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Column;
import com.malyvoj3.csvwvalidator.domain.model.Table;

import java.util.List;

public interface TableValidationRule {

    boolean init(Table table, List<Column> columns);

    void finishValidating();

    void addCell(String cellValue, int rowNumber, Column column);

    List<? extends ValidationError> createErrors();

}
