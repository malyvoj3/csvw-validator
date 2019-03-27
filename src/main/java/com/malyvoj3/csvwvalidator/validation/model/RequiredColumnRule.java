package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.model.Cell;
import com.malyvoj3.csvwvalidator.domain.model.Column;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RequiredColumnRule extends TableValidationRule {

    @Override
    public List<? extends ValidationError> validate(Table table) {
        List<ValidationError> errors = new ArrayList<>();
        List<Cell> invalidCells = new ArrayList<>();
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
        }
        return errors;
    }

}
