package com.malyvoj3.csvwvalidator.web.rest;

import lombok.Data;

import java.util.List;

@Data
public class ValidationRequest {

    private String tabularFileUrl;
    private List<String> metadataFilesUrl;

}
