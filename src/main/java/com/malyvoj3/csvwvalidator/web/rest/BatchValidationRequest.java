package com.malyvoj3.csvwvalidator.web.rest;

import com.malyvoj3.csvwvalidator.processor.ProcessingInput;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BatchValidationRequest {

    private List<ProcessingInput> filesToProcess = new ArrayList<>();
    private boolean strictMode = true;

}
