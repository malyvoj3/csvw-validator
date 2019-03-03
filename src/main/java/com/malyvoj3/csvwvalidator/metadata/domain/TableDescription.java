package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableDescription extends TopLevelDescription implements CommonDescription, CompatibleDescription<TableDescription> {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private LinkProperty url;
    private BooleanAtomicProperty suppressOutput;

    @Override
    public boolean isCompatibleWith(@NonNull TableDescription other) {
        return url.getNormalizedValue().equals(other.getUrl().getNormalizedValue())
                && getTableSchema().getNormalizedValue().isCompatibleWith(other.getTableSchema().getNormalizedValue());
    }
}
