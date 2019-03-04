package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaDescription extends InheritanceDescription implements CommonDescription, CompatibleDescription<SchemaDescription> {

    private List<CommonProperty> commonProperties = new ArrayList<>();

    private ArrayProperty<ColumnDescription> columns;

    private ColumnReferenceProperty primaryKey;

    private ColumnReferenceProperty rowTitles;

    private ArrayProperty<ForeignKeyDescription> foreignKeys;

    @Override
    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }

    @Override
    public boolean isCompatibleWith(@NonNull SchemaDescription other) {
        List<ColumnDescription> nonVirtualColumns = columns.getValue().stream()
                .filter(column -> !column.getVirtual().getValue())
                .collect(Collectors.toList());
        List<ColumnDescription> otherNonVirtualColumns = other.getColumns().getValue().stream()
                .filter(column -> !column.getVirtual().getValue())
                .collect(Collectors.toList());
        boolean compatible = false;
        if (nonVirtualColumns.size() == otherNonVirtualColumns.size()) {
            int length = nonVirtualColumns.size();
            compatible = true;
            for (int i = 0; i < length; i++) {
                ColumnDescription firstColumn = nonVirtualColumns.get(i);
                ColumnDescription secondColumn = otherNonVirtualColumns.get(i);
                compatible &= firstColumn.isCompatibleWith(secondColumn);
            }
        }
        return compatible;

    }

    @Override
    public void normalize(Context context) {
        super.normalize(context);
        commonProperties.forEach(commonProperty -> normalizeProperty(commonProperty, context));
        normalizeProperty(columns, context);
        normalizeProperty(primaryKey, context);
        normalizeProperty(rowTitles, context);
        normalizeProperty(foreignKeys, context);
    }
}
