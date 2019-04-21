package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.CompatibleDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ColumnReferenceProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaDescription extends InheritanceDescription implements CompatibleDescription<SchemaDescription> {

    private ArrayProperty<ColumnDescription> columns;

    private ColumnReferenceProperty primaryKey;

    private ColumnReferenceProperty rowTitles;

    private ArrayProperty<ForeignKeyDescription> foreignKeys;

    @Override
    public boolean isCompatibleWith(@NonNull SchemaDescription other) {
        boolean compatible = false;
        if (columns != null && other.columns != null) {
            List<ColumnDescription> nonVirtualColumns = columns.getValue().stream()
                    .filter(column -> column.getVirtual() == null || !column.getVirtual().getValue())
                    .collect(Collectors.toList());
            List<ColumnDescription> otherNonVirtualColumns = other.getColumns().getValue().stream()
                    .filter(column -> column.getVirtual() == null ||!column.getVirtual().getValue())
                    .collect(Collectors.toList());
            if (nonVirtualColumns.size() == otherNonVirtualColumns.size()) {
                int length = nonVirtualColumns.size();
                compatible = true;
                for (int i = 0; i < length; i++) {
                    ColumnDescription firstColumn = nonVirtualColumns.get(i);
                    ColumnDescription secondColumn = otherNonVirtualColumns.get(i);
                    compatible &= firstColumn.isCompatibleWith(secondColumn);
                }
            }
        }
        return compatible;

    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(columns, context));
        normalizationErrors.addAll(normalizeProperty(primaryKey, context));
        normalizationErrors.addAll(normalizeProperty(rowTitles, context));
        normalizationErrors.addAll(normalizeProperty(foreignKeys, context));
        return normalizationErrors;
    }

    @Override
    public String getValidType() {
        return "Schema";
    }
}
