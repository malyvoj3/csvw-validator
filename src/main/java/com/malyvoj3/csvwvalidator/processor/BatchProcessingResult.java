package com.malyvoj3.csvwvalidator.processor;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BatchProcessingResult implements BatchResult<ProcessingResult> {

    private long filesCount;
    private long passedFilesCount;
    private long warningFilesCount;
    private long errorFilesCount;
    private List<ProcessingResult> filesResults = new ArrayList<>();

}
