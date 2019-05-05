package com.malyvoj3.csvwvalidator.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {

    private String id;
    private ValidationResponse result;

}
