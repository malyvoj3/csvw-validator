package com.malyvoj3.csvwvalidator.web.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchValidationResponse {

    private Long filesCount;
    private Long passedFilesCount;
    private Long warningFilesCount;
    private Long errorFilesCount;
    private List<ValidationResponse> filesResults = new ArrayList<>();

}
