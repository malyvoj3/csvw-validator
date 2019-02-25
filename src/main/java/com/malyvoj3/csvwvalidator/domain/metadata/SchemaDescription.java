package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaDescription extends InheritanceDescription implements CommonDescription {

    private List<CommonProperty> commonProperties = new ArrayList<>();
    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private ArrayProperty<ColumnDescription> columns;

    private ColumnReferenceProperty privateKey;

    private ColumnReferenceProperty rowTitles;

    private ArrayProperty<ForeignKeyDescription> foreignKeys;

    @Override
    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }
}
