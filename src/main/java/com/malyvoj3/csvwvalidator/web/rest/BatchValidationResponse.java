package com.malyvoj3.csvwvalidator.web.rest;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BatchValidationResponse {

    private long filesCount;
    private long passedFilesCount;
    private long warningFilesCount;
    private long errorFilesCount;
    private List<ValidationResponse> filesResults = new ArrayList<>();

}
