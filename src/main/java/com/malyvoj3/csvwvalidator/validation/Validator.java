package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.parser.csv.CsvParsingResult;
import com.malyvoj3.csvwvalidator.parser.metadata.MetadataParsingResult;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public List<ValidationError> validate(CsvParsingResult tabular, MetadataParsingResult metadata) {
        List<ValidationError> validationErrors = new ArrayList<>();

        return validationErrors;
    }

}
