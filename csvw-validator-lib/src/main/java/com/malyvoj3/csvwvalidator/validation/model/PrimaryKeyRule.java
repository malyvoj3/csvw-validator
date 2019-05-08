package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Column;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrimaryKeyRule implements TableValidationRule {

    private Set<String> primaryKeys = new HashSet<>();
    private Set<Integer> primaryColumns = new HashSet<>();

    @Getter
    private boolean isActive = true;
    private boolean isValid = true;
    private boolean initialized = false;
    private boolean finished = false;

    private Integer failedRow;
    private int rowCount = 1;
    private StringBuilder tmpPrimaryKey = new StringBuilder();

    @Override
    public boolean init(Table table, List<Column> columns) {
        initialized = true;
        primaryColumns = columns.stream()
                .filter(Column::isPrimaryKey)
                .map(Column::getNumber)
                .collect(Collectors.toSet());
        isActive = !primaryColumns.isEmpty();
        return isActive;
    }

    @Override
    public void finishValidating() {
        finished = true;
        // Check if last row primary key is not valid.
        if (isValid && !primaryKeys.add(tmpPrimaryKey.toString())) {
            failedRow = rowCount;
            isValid = false;
            primaryKeys = null;
            tmpPrimaryKey = null;
        }
    }

    @Override
    public void addCell(String cellValue, int rowNumber, Column column) {
        if (!initialized) {
            throw new IllegalStateException("Validation rule was not initialized.");
        }

        if (isValid && primaryColumns.contains(column.getNumber())) {
            if (rowNumber == rowCount) {
                tmpPrimaryKey.append(cellValue);
            } else {
                if (!primaryKeys.add(tmpPrimaryKey.toString())) {
                    // Stop validating, it is already wrong.
                    failedRow = rowNumber;
                    isValid = false;
                    primaryKeys = null;
                    tmpPrimaryKey = null;
                }
                rowCount++;
                tmpPrimaryKey = new StringBuilder(cellValue);
            }
        }
    }

    @Override
    public List<? extends ValidationError> createErrors() {
        if (!finished) {
            throw new IllegalStateException("Validation rule was not finished.");
        }

        if (isValid) {
            return Collections.emptyList();
        } else {
            return Collections.singletonList(ValidationError.fatal("error.primaryKey", failedRow));
        }
    }
}
