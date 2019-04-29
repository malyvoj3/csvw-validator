package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class TopLevelDescriptionValidationRule<T extends TopLevelDescription> implements MetadataValidationRule<T> {

    protected List<InheritanceDescription> getInheritanceDescriptions(TableDescription table) {
        List<InheritanceDescription> inheritanceDescriptions = new ArrayList<>();
        inheritanceDescriptions.add(table);
        SchemaDescription schema = Optional.ofNullable(table)
                .map(TopLevelDescription::getTableSchema)
                .map(Property::getValue)
                .orElse(null);
        if (schema != null) {
            inheritanceDescriptions.add(schema);
        }

        Optional.ofNullable(schema)
                .map(SchemaDescription::getColumns)
                .map(Property::getValue)
                .ifPresent(inheritanceDescriptions::addAll);
        return inheritanceDescriptions;
    }

    protected List<CommonProperty> getCommonProperties(TableDescription table) {
        List<InheritanceDescription> inheritanceDescriptions = getInheritanceDescriptions(table);
        List<CommonProperty> commonProperties = new ArrayList<>(table.getCommonProperties());
        for (InheritanceDescription desc : inheritanceDescriptions) {
            commonProperties.addAll(desc.getCommonProperties());
            Optional.of(desc)
                    .map(InheritanceDescription::getDataType)
                    .map(Property::getValue)
                    .ifPresent(dataType -> commonProperties.addAll(dataType.getCommonProperties()));
        }
        return commonProperties;
    }

}
