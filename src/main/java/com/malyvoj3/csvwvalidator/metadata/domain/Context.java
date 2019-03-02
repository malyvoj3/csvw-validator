package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;
import lombok.Data;

@Data
public class Context {

    private StringAtomicProperty refContext;
    private StringAtomicProperty base;
    private StringAtomicProperty language;

}
