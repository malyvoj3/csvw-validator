package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableDescription extends TopLevelDescription implements CompatibleDescription<TableDescription> {

    private LinkProperty url;
    private BooleanAtomicProperty suppressOutput;

    @Override
    public boolean isCompatibleWith(@NonNull TableDescription other) {
        return url.getValue().equals(other.getUrl().getValue())
                && getTableSchema().getValue().isCompatibleWith(other.getTableSchema().getValue());
    }

    @Override
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(url, context);
        normalizeProperty(suppressOutput, context);
    }
}
