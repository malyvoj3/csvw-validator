package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.CompatibleDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.Normalizable;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableGroupDescription extends TopLevelDescription implements CompatibleDescription<TableGroupDescription>, Normalizable {

    private ArrayProperty<TableDescription> tables;

    @Override
    public boolean isCompatibleWith(@NonNull TableGroupDescription other) {
        for (TableDescription table : tables.getValue()) {
            for (TableDescription otherTable : other.getTables().getValue()) {
                if (table.isCompatibleWith(otherTable)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        // TODO setni tabulkam schema nemaji-li ho
        normalizationErrors.addAll(normalizeProperty(tables, context));
        return normalizationErrors;
    }
}
