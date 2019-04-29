package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.domain.model.TableGroup;

import java.util.List;

public interface TableGroupValidationRule {

    boolean init(TableGroup tableGroup);

    void finishValidating();

    void addTable(Table table);

    List<? extends ValidationError> createErrors();
}
