package com.malyvoj3.csvwvalidator.domain.metadata;

import java.util.ArrayList;
import java.util.List;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.BooleanAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnDescription extends InheritanceDescription implements CommonDescription, CompatibleDescription<ColumnDescription> {

    private List<CommonProperty> commonProperties = new ArrayList<>();

    private StringAtomicProperty name;

    private BooleanAtomicProperty suppressOutput;

    private NaturalLanguageProperty titles;

    private BooleanAtomicProperty virtual;

    @Override
    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }

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
    public void normalize(Context context) {
        super.normalize(context);
        commonProperties.forEach(commonProperty -> normalizeProperty(commonProperty, context));
        normalizeProperty(titles, context);
    }
}
