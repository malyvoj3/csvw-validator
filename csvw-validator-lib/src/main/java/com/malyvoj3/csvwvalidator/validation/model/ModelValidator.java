package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularParsingResult;

import java.util.List;

/**
 * Class which validates annotated model created from tabular data and metadata schema.
 */
public interface ModelValidator {

    /**
     * Validates tabular data with table group description.
     * @param csvParsingResults Result of parsing CSV file.
     * @param tableGroupDescription Description of table group, which contains parsed data.
     * @return List of validation errors.
     */
    List<? extends ValidationError> validateTableGroup(List<TabularParsingResult> csvParsingResults,
                                                       TableGroupDescription tableGroupDescription);

    /**
     * Validates tabular data with group description.
     * @param csvParsingResult Result of parsing CSV file.
     * @param tableDescription Description of table, which contains parsed data.
     * @return List of validation errors.
     */
    List<? extends ValidationError> validateTable(TabularParsingResult csvParsingResult,
                                                  TableDescription tableDescription);

}
