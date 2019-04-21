package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.domain.model.TableGroup;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularParsingResult;

import java.util.List;

public interface AnnotationCreator {

    TableGroup createAnnotations(List<TabularParsingResult> csvParsingResults,
                                 TableGroupDescription tableGroupDescription);

    Table createAnnotations(TabularParsingResult csvParsingResult, TableDescription tableDescription);
}
