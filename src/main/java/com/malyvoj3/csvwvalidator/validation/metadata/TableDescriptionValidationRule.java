package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.validation.ValidationError;

public interface TableDescriptionValidationRule {

    ValidationError validate(TableDescription tableDescription);

}
