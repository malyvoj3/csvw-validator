package com.malyvoj3.csvwvalidator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CellError {

    private String code;
    private String message;
}
