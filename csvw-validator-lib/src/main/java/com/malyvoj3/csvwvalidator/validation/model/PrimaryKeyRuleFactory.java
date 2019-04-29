package com.malyvoj3.csvwvalidator.validation.model;

public class PrimaryKeyRuleFactory implements TableRuleFactory {

    @Override
    public TableValidationRule createRule() {
        return new PrimaryKeyRule();
    }

}
