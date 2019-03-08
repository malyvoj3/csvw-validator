package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

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
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(url, context));
        normalizationErrors.addAll(normalizeProperty(suppressOutput, context));
        return normalizationErrors;
    }
}
