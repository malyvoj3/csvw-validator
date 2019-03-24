package com.malyvoj3.csvwvalidator.parser.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TopLevelDescription;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MetadataParsingResult {

    private TopLevelDescription topLevelDescription;
    private List<JsonParserError> parsingErrors = new ArrayList<>();
    private List<ValidationError> normalizationErrors = new ArrayList<>();
    private TopLevelType topLevelType;

}
