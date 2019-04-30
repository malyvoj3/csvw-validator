package com.malyvoj3.csvwvalidator.processor.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedError {

    private String severity;
    private String message;

}
