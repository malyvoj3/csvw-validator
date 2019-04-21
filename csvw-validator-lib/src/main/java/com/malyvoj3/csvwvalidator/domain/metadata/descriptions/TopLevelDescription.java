package com.malyvoj3.csvwvalidator.domain.metadata.descriptions;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TopLevelDescription extends InheritanceDescription {

    private Context context;

    private ObjectProperty<DialectDescription> dialect;

    private ArrayProperty<NoteDescription> notes;

    private StringAtomicProperty tableDirection;

    private ObjectProperty<SchemaDescription> tableSchema;

    private ArrayProperty<TransformationDescription> transformations;

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        normalizationErrors.addAll(normalizeProperty(dialect, context));
        normalizationErrors.addAll(normalizeProperty(tableDirection, context));
        normalizationErrors.addAll(normalizeProperty(tableSchema, context));
        normalizationErrors.addAll(normalizeProperty(transformations, context));
        return normalizationErrors;
    }

    public abstract boolean describesTabularData(String tabularUrl);
}
