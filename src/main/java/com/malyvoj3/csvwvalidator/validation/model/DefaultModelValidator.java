package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.model.*;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultModelValidator implements ModelValidator {

    @Autowired(required = false)
    private List<TableValidationRule> tableRules = new ArrayList<>();

    @Autowired(required = false)
    private List<TableGroupValidationRule> tableGroupRules = new ArrayList<>();

    @Override
    public List<? extends ValidationError> validateTable(@NonNull Table table) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (TableValidationRule rule : tableRules) {
            validationErrors.addAll(rule.validate(table));
        }
        validationErrors.addAll(getCellErrors(table));
        return validationErrors;
    }

    private List<? extends ValidationError> getCellErrors(Table table) {
        List<ValidationError> validationErrors = new ArrayList<>();
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            for (Cell cell : column.getCells()) {
                for (CellError cellError : cell.getErrors()) {
                    String errorMsg = String.format("%s", cellError.getMessage());
                    validationErrors.add(ValidationError.error(errorMsg));
                }
            }
        }
        return validationErrors;
    }

    @Override
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
