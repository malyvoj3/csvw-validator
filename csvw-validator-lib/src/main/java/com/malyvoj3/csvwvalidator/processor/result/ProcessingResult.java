package com.malyvoj3.csvwvalidator.processor.result;

import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import lombok.Data;

import java.util.List;

@Data
public class ProcessingResult implements Result {

    private String tabularUrl;
    private String metadataUrl;
    private ValidationStatus validationStatus;
    private List<LocalizedError> errors;
    private long warningCount;
    private long errorCount;
    private long fatalCount;
    private long totalErrorsCount;
    private Long rowsNumber;
    private Long columnsNumber;
    private long tablesNumber;
    private String usedLanguage;
    private boolean strictMode;

}
