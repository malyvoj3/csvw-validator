package com.malyvoj3.csvwvalidator.validation.model;

/**
 * Factory which creates table group validation rules.
 */
public interface TableGroupRuleFactory {

    /**
     * Create table group validation rule.
     * @return
     */
    TableGroupValidationRule createRule();

}
