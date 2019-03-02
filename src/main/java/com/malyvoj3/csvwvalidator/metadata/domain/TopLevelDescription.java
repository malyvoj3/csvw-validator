package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.ArrayProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.CommonProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.ObjectProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class TopLevelDescription extends InheritanceDescription implements CommonDescription {

    private Context context;
    private List<CommonProperty> commonProperties = new ArrayList<>();

    private ObjectProperty<DialectDescription> dialect;

    private ArrayProperty<CommonProperty> notes;

    private StringAtomicProperty tableDirection;

    private ObjectProperty<SchemaDescription> tableSchema;

    private ArrayProperty<TransformationDescription> transformations;

    @Override
    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }
}
