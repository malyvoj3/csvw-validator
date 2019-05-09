package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.model.Column;
import com.malyvoj3.csvwvalidator.domain.model.Table;

import java.util.List;

/**
 * Validation rule for table.
 */
public interface TableValidationRule {

    /**
     * Init this rules before starting processing the table.
     * @param table Table which is validated.
     * @param columns Columns of the table.
     * @return True if this rule is active and should be validated, otherwise false.
     */
    boolean init(Table table, List<Column> columns);

    /**
     * Contains some extra work after the table is processed.
     */
    void finishValidating();

    /**
     * Add cell which is being currently processed by validator.
     * @param cellValue String value of cell.
     * @param rowNumber Row number of the cell.
     * @param column Column of the cell.
     */
    void addCell(String cellValue, int rowNumber, Column column);

    List<? extends ValidationError> createErrors();

}
