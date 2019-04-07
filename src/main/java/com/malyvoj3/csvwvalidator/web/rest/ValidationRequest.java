package com.malyvoj3.csvwvalidator.web.rest;

import lombok.Data;

@Data
public class ValidationRequest {

    private String tabularUrl;
    private String metadataUrl;
    private boolean strictMode = true;

}
