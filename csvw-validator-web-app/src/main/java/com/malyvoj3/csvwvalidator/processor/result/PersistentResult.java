package com.malyvoj3.csvwvalidator.processor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersistentResult extends ProcessingResult {

    private String id;

}
