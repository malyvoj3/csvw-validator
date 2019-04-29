package com.malyvoj3.csvwvalidator.validation.model;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularParsingResult;

import java.util.List;

public interface ModelValidator {

    List<? extends ValidationError> validateTableGroup(List<TabularParsingResult> csvParsingResults,
                                                       TableGroupDescription tableGroupDescription);

    List<? extends ValidationError> validateTable(TabularParsingResult csvParsingResult,
                                                  TableDescription tableDescription);

}
