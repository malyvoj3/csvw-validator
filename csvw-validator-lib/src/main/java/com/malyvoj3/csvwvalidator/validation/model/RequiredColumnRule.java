package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Table;

import java.util.ArrayList;
import java.util.List;

public class RequiredColumnRule extends TableValidationRule {

    @Override
    public List<? extends ValidationError> validate(Table table) {
        List<ValidationError> errors = new ArrayList<>();
        // TODO asi uz neni potreba, validace probiha pri vytvareni Cell.
        /*List<Cell> invalidCells = new ArrayList<>();
        for (Column column : table.getColumns()) {
            if (column.isRequired()) {
                for (Cell cell : column.getCells()) {
                    if (StringUtils.isEmpty(cell.getStringValue())) {
                        invalidCells.add(cell);
                    }
                }
            }
        }
        for (Cell cell : invalidCells) {
            errors.add(ValidationError.fatal(String.format(
                    "Table '%s' has empty value in row '%d' and column '%s'. Column is required.",
                    table.getUrl(), cell.getRow().getNumber(), cell.getColumn().getName())));
        }*/
        return errors;
    }

}
