package com.malyvoj3.csvwvalidator.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FormatParsingResult {

    private BigDecimal value;
    private boolean isNan;
    private boolean isNegInf;
    private boolean isPosInf;

}
