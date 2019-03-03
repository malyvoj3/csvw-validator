package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableGroupDescription extends TopLevelDescription implements CommonDescription, CompatibleDescription<TableGroupDescription> {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private ArrayProperty<TableDescription> tables;

    @Override
    public boolean isCompatibleWith(@NonNull TableGroupDescription other) {
        for (TableDescription table : tables.getNormalizedValue()) {
            for (TableDescription otherTable : other.getTables().getNormalizedValue()) {
                if (table.isCompatibleWith(otherTable)) {
                    return true;
                }
            }
        }
        return false;
    }
}
