package com.malyvoj3.csvwvalidator.domain;

import lombok.Data;

@Data
public class FileResponse {

    private String url;
    private byte[] content;
    private String filePath;
    private boolean isRemoteFile = true;
    private int responseCode;
    private ContentType contentType;
    private Link link;
}
