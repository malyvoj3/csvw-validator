package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;

import java.util.*;

public class ColumnNamesRule extends TableDescriptionValidationRule {

    @Override
    public List<? extends ValidationError> validate(TableDescription table) {
        List<ValidationError> errors = new ArrayList<>();
        List<ColumnDescription> columns = Optional.ofNullable(table)
                .map(TopLevelDescription::getTableSchema)
                .map(Property::getValue)
                .map(SchemaDescription::getColumns)
                .map(Property::getValue)
                .orElse(Collections.emptyList());
        Set<String> columnNames = new HashSet<>();
        for (ColumnDescription column : columns) {
            String columnName = Optional.of(column)
                    .map(ColumnDescription::getName)
                    .map(Property::getValue)
                    .orElse(null);
            if (columnName != null) {
                if (columnNames.contains(columnName)) {
                    errors.add(ValidationError.fatal("There are more columns with name '%s'", columnName));
                } else {
                    columnNames.add(columnName);
                }
            }
        }
        return errors;
    }

}
