package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.domain.model.TableGroup;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ModelValidator {

    @Autowired(required = false)
    private List<TableValidationRule> tableRules = new ArrayList<>();

    @Autowired(required = false)
    private List<TableGroupValidationRule> tableGroupRules = new ArrayList<>();

    public List<? extends ValidationError> validateTable(@NonNull Table table) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (TableValidationRule rule : tableRules) {
            validationErrors.addAll(rule.validate(table));
        }
        return validationErrors;
    }

    public List<? extends ValidationError> validateTableGroup(@NonNull TableGroup tableGroup) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (TableGroupValidationRule rule : tableGroupRules) {
            validationErrors.addAll(rule.validate(tableGroup));
        }
        List<Table> tables = tableGroup.getTables();
        for (Table table : tables) {
            validationErrors.addAll(validateTable(table));
        }
        return validationErrors;
    }

}
