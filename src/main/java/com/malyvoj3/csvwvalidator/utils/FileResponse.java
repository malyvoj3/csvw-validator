package com.malyvoj3.csvwvalidator.utils;

import lombok.Data;

@Data
public class FileResponse {

    private int responseCode;
    private byte[] content;
    private ContentType contentType;
    private Link link;
}
