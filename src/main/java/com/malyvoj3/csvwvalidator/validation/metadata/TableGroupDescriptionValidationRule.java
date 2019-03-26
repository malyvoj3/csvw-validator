package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.validation.ValidationError;

public interface TableGroupDescriptionValidationRule {

    ValidationError validate(TableGroupDescription tableGroupDescription);

}
