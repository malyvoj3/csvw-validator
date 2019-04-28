package com.malyvoj3.csvwvalidator.web.view;

import lombok.Data;

@Data
public class ValidatingDataDTO {

    private String csvUrl;
    private String metadataUrl;
    private String csvFileName;
    private String csvFilePath;
    private String metadataFileName;
    private String metadatFilePath;
    private Boolean strictMode;

}
