package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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
    public void normalize(Context context) {
        super.normalize(context);
        // TODO setni tabulkam schema nemaji-li ho
        normalizeProperty(tables, context);
    }
}
