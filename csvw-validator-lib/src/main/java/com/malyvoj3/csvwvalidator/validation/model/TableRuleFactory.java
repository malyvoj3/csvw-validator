package com.malyvoj3.csvwvalidator.validation.model;

/**
 * Factory which creates table validation rules.
 */
public interface TableRuleFactory {

    /**
     * Create group validation rule.
     * @return
     */
    TableValidationRule createRule();

}
