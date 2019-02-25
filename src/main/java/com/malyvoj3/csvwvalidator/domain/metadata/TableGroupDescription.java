package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableGroupDescription extends InheritanceDescription implements TopLevelDescription, CommonDescription {

    private Context context;
    private List<CommonProperty> commonProperties = new ArrayList<>();

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;


    private ArrayProperty<TableDescription> tables;

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
