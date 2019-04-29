package com.malyvoj3.csvwvalidator.processor;

import lombok.Data;

import java.util.Locale;

@Data
public class ProcessingSettings {

    private boolean strictMode = true;
    private Locale locale = new Locale("en", "GB");

}
