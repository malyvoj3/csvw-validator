package com.malyvoj3.csvwvalidator.parser.csv;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.model.Table;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TabularParsingResult {

    private String tabularUrl;
    private List<ValidationError> parsingErrors = new ArrayList<>();
    private Table table;
    private TableDescription tableDescription;

}
