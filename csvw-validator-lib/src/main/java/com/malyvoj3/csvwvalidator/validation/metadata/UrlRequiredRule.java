package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;

import java.util.ArrayList;
import java.util.List;

public class UrlRequiredRule extends TableDescriptionValidationRule {

    @Override
    public List<ValidationError> validate(TableDescription description) {
        List<ValidationError> errors = new ArrayList<>();
        if (description.getUrl() == null) {
            errors.add(ValidationError.fatal("Table property 'url' is required."));
        }
        return errors;
    }

}
