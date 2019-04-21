package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.domain.model.TableGroup;

import java.util.List;

public interface ModelValidator {

    public List<? extends ValidationError> validateTable(Table table);

    public List<? extends ValidationError> validateTableGroup(TableGroup tableGroup);
}
