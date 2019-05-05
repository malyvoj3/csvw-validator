package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.domain.Severity;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularParsingResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProcessingContext {

    private boolean fatal = false;
    private Long rowsNumber = 0L;
    private Long columnsNumber = 0L;
    private Long tablesNumber = 0L;
    private List<ValidationError> errors = new ArrayList<>();

    private final ProcessingSettings settings;

    public void addTabularResult(TabularParsingResult tabularParsingResult) {
        rowsNumber = rowsNumber + tabularParsingResult.getRowsNumber();
        columnsNumber = columnsNumber + tabularParsingResult.getColumnsNumber();
        tablesNumber++;
        addErrors(tabularParsingResult.getParsingErrors());
    }

    public void addErrors(List<? extends ValidationError> errorsToAdd) {
        for (ValidationError error : errorsToAdd) {
            addError(error);
        }
    }

    public <T extends ValidationError> void addError(T errorToAdd) {
        if (!fatal && Severity.FATAL.equals(errorToAdd.getSeverity())) {
            fatal = true;
        }
        errors.add(errorToAdd);
    }

    public boolean isNotFatal() {
        return !isFatal();
    }

}
