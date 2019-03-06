package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    public void normalize(Context context) {
        super.normalize(context);
        normalizeProperty(dialect, context);
        normalizeProperty(tableDirection, context);
        normalizeProperty(tableSchema, context);
        normalizeProperty(transformations, context);
    }
}