package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrimaryKeyReferenceRule extends TableDescriptionValidationRule {

    @Override
    public List<? extends ValidationError> validate(TableDescription table) {
        List<ValidationError> errors = new ArrayList<>();
        List<ColumnDescription> columns = Optional.ofNullable(table)
                .map(TopLevelDescription::getTableSchema)
                .map(Property::getValue)
                .map(SchemaDescription::getColumns)
                .map(Property::getValue)
                .orElse(Collections.emptyList());
        Map<String, ColumnDescription> columnMap = columns.stream()
                .filter(column -> column.getName() != null && column.getName().getValue() != null)
                .collect(Collectors.toMap(column -> column.getName().getValue(), Function.identity(), (o, o2) -> o2));
        List<String> primaryKeys = Optional.ofNullable(table)
                .map(TopLevelDescription::getTableSchema)
                .map(Property::getValue)
                .map(SchemaDescription::getPrimaryKey)
                .map(Property::getValue)
                .orElse(Collections.emptyList());
        for (String primaryKey : primaryKeys) {
            if (columnMap.get(primaryKey) == null) {
                errors.add(ValidationError.warn("error.invalidReference", primaryKey));
            }
        }
        return errors;
    }
}
