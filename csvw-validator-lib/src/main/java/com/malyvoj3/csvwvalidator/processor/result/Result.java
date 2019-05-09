package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.domain.ValidationStatus;

/**
 * Result of the validation.
 */
public interface Result {

    /**
     *
     * @return Status of the validation.
     */
    ValidationStatus getValidationStatus();
}
