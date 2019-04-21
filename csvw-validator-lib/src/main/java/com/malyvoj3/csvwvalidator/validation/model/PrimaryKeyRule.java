package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Cell;
import com.malyvoj3.csvwvalidator.domain.model.Row;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PrimaryKeyRule extends TableValidationRule {

    @Override
    public List<? extends ValidationError> validate(Table table) {
        List<ValidationError> errors = new ArrayList<>();
        List<Row> invalidRows = new ArrayList<>();
        HashSet<String> primaryKeys = new HashSet<>();
        for (Row row : table.getRows()) {
            if (row.getPrimaryKey() != null && row.getPrimaryKey().size() > 0) {
                String primaryKeyString = StringUtils.join(row.getPrimaryKey().stream()
                        .map(Cell::getStringValue)
                        .collect(Collectors.toList()));
                if (primaryKeys.contains(primaryKeyString)) {
                    invalidRows.add(row);
                } else {
                    primaryKeys.add(primaryKeyString);
                }
            }
        }
        for (Row row : invalidRows) {
            errors.add(ValidationError.fatal(String.format(
                    "Primary key violation. Duplicated primary key in row %d.", row.getNumber())));
        }
        return errors;
    }

}
