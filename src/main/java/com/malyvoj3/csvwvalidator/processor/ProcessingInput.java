package com.malyvoj3.csvwvalidator.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingInput {

    private String tabularUrl;
    private String metadataUrl;

}
