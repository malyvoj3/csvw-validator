package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;

import java.util.ArrayList;
import java.util.List;

public class TablesRequiredRule extends TableGroupDescriptionValidationRule {

    @Override
    public List<ValidationError> validate(TableGroupDescription description) {
        List<ValidationError> errors = new ArrayList<>();
        boolean isValid = description.getTables() != null
                && description.getTables().getValue() != null
                && description.getTables().getValue().size() > 0;
        if (!isValid) {
            errors.add(ValidationError.fatal("Table group property 'tables' is required and must contain at least one table."));
        }
        return errors;
    }

}
