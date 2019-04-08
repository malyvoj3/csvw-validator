package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.metadata.CompatibleDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableDescription extends TopLevelDescription implements CompatibleDescription<TableDescription> {

    private LinkProperty url;
    private BooleanAtomicProperty suppressOutput;

    @Override
    public boolean isCompatibleWith(@NonNull TableDescription other) {
        String tableUrl = Optional.ofNullable(url)
                .map(Property::getValue)
                .orElse(null);
        String otherTableUrl = Optional.ofNullable(other)
                .map(TableDescription::getUrl)
                .map(Property::getValue)
                .orElse(null);
        SchemaDescription tableSchema = Optional.ofNullable(getTableSchema())
                .map(Property::getValue)
                .orElse(null);
        SchemaDescription otherTableSchema = Optional.ofNullable(other)
                .map(TopLevelDescription::getTableSchema)
                .map(Property::getValue)
                .orElse(null);
        return tableUrl != null && tableUrl.equals(otherTableUrl)
                && tableSchema != null && tableSchema.isCompatibleWith(otherTableSchema);
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(url, context));
        normalizationErrors.addAll(normalizeProperty(suppressOutput, context));
        return normalizationErrors;
    }

    @Override
    public boolean describesTabularData(String tabularUrl) {
        return UriUtils.uriEquals(url.getValue(), tabularUrl);
    }
}
