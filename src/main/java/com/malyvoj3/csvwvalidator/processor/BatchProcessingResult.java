package com.malyvoj3.csvwvalidator.processor;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BatchProcessingResult {

    private long filesCount;
    private long passedCount;
    private long warningCount;
    private long errorCount;
    private List<ProcessingResult> filesResults = new ArrayList<>();

}
