package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DefaultMetadataValidator implements MetadataValidator {

    @Autowired(required = false)
    private List<TableDescriptionValidationRule> tableRules = new ArrayList<>();

    @Autowired(required = false)
    private List<TableGroupDescriptionValidationRule> tableGroupRules = new ArrayList<>();

    @Override
    public List<? extends ValidationError> validateTable(@NonNull TableDescription tableDescription) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (TableDescriptionValidationRule rule : tableRules) {
            validationErrors.addAll(rule.validate(tableDescription));
        }
        return validationErrors;
    }

    @Override
    public List<? extends ValidationError> validateTableGroup(@NonNull TableGroupDescription tableGroupDescription) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (TableGroupDescriptionValidationRule rule : tableGroupRules) {
            validationErrors.addAll(rule.validate(tableGroupDescription));
        }
        List<TableDescription> tableDescriptions = Optional.ofNullable(tableGroupDescription.getTables())
                .map(Property::getValue)
                .orElse(Collections.emptyList());
        for (TableDescription tableDescription : tableDescriptions) {
            validationErrors.addAll(validateTable(tableDescription));
        }
        return validationErrors;
    }

}
