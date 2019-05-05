package com.malyvoj3.csvwvalidator.processor.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BatchProcessingResult<T extends ProcessingResult> implements BatchResult<T> {

    private long filesCount;
    private long passedFilesCount;
    private long warningFilesCount;
    private long errorFilesCount;
    private List<T> filesResults = new ArrayList<>();

}
