package com.malyvoj3.csvwvalidator.processor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BatchProcessingResult {

    private long filesNum;
    private long passedNum;
    private long warningNum;
    private long errorNum;
    private List<ProcessingResult> filesResults = new ArrayList<>();

}
