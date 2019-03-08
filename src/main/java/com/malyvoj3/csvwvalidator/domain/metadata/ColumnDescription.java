package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnDescription extends InheritanceDescription implements CompatibleDescription<ColumnDescription> {

    private StringAtomicProperty name;

    private BooleanAtomicProperty suppressOutput;

    private NaturalLanguageProperty titles;

    private BooleanAtomicProperty virtual;

    @Override
    public boolean isCompatibleWith(@NonNull ColumnDescription other) {
        boolean compatible;
        if (name == null && titles == null && other.getName() == null && other.getTitles() == null) {
            compatible = true;
        } else if ((name == null && other.getName() == null) || name.equals(other.getName())) {
            compatible = true;
        } else {
            compatible = titles.hasIntersectionWith(other.getTitles());
        }
        return compatible;
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(titles, context));
        return normalizationErrors;
    }
}
