package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.*;
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
    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

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
        List<ColumnDescription> nonVirtualColumns = columns.getNormalizedValue().stream()
                .filter(column -> !column.getVirtual().getNormalizedValue())
                .collect(Collectors.toList());
        List<ColumnDescription> otherNonVirtualColumns = other.getColumns().getNormalizedValue().stream()
                .filter(column -> !column.getVirtual().getNormalizedValue())
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
}
