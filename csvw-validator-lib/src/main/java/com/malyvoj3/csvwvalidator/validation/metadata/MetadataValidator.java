package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;

import java.util.List;

/**
 * Validator for metadata.
 */
public interface MetadataValidator {

    /**
     * Validates table description.
     * @param tableDescription Table description which should be validated.
     * @return List of validation errors.
     */
    List<? extends ValidationError> validateTable(TableDescription tableDescription);

    /**
     * Validates table group description.
     * @param tableGroupDescription Table group description which should be validated.
     * @return List of validation errors.
     */
    List<? extends ValidationError> validateTableGroup(TableGroupDescription tableGroupDescription);
}
